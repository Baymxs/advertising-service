package ru.nsu.bayramov.advertisingservice.banner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.nsu.bayramov.advertisingservice.dtotransformservice.DtoTransformService;
import ru.nsu.bayramov.advertisingservice.model.domain.Banner;
import ru.nsu.bayramov.advertisingservice.model.domain.Category;
import ru.nsu.bayramov.advertisingservice.model.domain.Request;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerCreationDto;
import ru.nsu.bayramov.advertisingservice.model.dto.BannerDto;
import ru.nsu.bayramov.advertisingservice.model.dto.ContentDto;
import ru.nsu.bayramov.advertisingservice.repositories.BannerRepository;
import ru.nsu.bayramov.advertisingservice.repositories.CategoryRepository;
import ru.nsu.bayramov.advertisingservice.repositories.RequestRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BannerService {
    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;
    private final DtoTransformService dtoTransformService;
    private final RequestRepository requestRepository;

    public void create(BannerCreationDto bannerCreationDto) {
        bannerCreationDto.setDeleted(false);

        Category category = categoryRepository.
                findById(bannerCreationDto.getCategoryId()).
                orElseThrow(() -> new IllegalArgumentException("Category with category id " + bannerCreationDto.getCategoryId() + " not found"));

        Banner banner = dtoTransformService.convertToBanner(bannerCreationDto);
        banner.setCategory(category);

        bannerRepository.save(banner);
    }

    public void update(BannerDto bannerDto) {
        bannerRepository.save(dtoTransformService.convertToBanner(bannerDto));
    }

    public List<BannerDto> search(String searchString) {
        List<BannerDto> banners = new ArrayList<>();

        for (Banner banner : bannerRepository.findAllByDeletedFalse()) {
            String bannerName = banner.getName().toLowerCase();

            if (bannerName.contains(searchString.toLowerCase())) {
                banners.add(dtoTransformService.convertToBannerDto(banner));
            }
        }

        return banners;
    }

    public ContentDto getBannerContent(String categoryRequestName, String clientIp, String userAgent) {
        System.out.println(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());
        Category category = categoryRepository.findByRequestName(categoryRequestName);

        Collection<Banner> banners = bannerRepository.findAllByCategoryAndDeletedFalse(category);

        if (banners.size() == 0) {
            throw new IllegalArgumentException("Banners with category " + categoryRequestName + " not found");
        }

        if (banners.size() == 1) {
            Banner banner = banners.iterator().next();

            if (checkUserDailyBannerState(banner, clientIp, userAgent)) {
                createRequest(banner, clientIp, userAgent);
                return new ContentDto(banner.getContent());
            }
        }

        List<Banner> bannersWithTheSamePrice = new LinkedList<>();
        double maxPrice = 0.0;
        for (Banner banner : banners) {
            double actualBannerPrice = banner.getPrice().doubleValue();
            if (actualBannerPrice > maxPrice) {
                maxPrice = actualBannerPrice;
                bannersWithTheSamePrice.clear();
                bannersWithTheSamePrice.add(banner);
            } else if (actualBannerPrice == maxPrice) {
                bannersWithTheSamePrice.add(banner);
            }
        }

        if (banners.size() == 1) {
            createRequest(bannersWithTheSamePrice.get(1), clientIp, userAgent);
            return new ContentDto(bannersWithTheSamePrice.get(1).getContent());
        } else {
            Random rand = new Random();
            Banner banner = bannersWithTheSamePrice.get(rand.nextInt(bannersWithTheSamePrice.size()));

            createRequest(banner, clientIp, userAgent);

            return new ContentDto(banner.getContent());
        }
    }

    private void createRequest(Banner banner, String clientIp, String userAgent) {
        Request request = new Request();
        request.setBanner(banner);
        request.setUserAgent(userAgent);
        request.setIpAddress(clientIp);
        request.setDate(new Date());

        requestRepository.save(request);
    }

    private boolean checkUserDailyBannerState(Banner banner, String clientIp, String userAgent) {
        Request request = requestRepository.findByBannerAndUserAgentAndAndIpAddress(banner, clientIp, userAgent);

        if (request == null) {
            return true;
        } else return calculateNumberOfDaysBetween(new Date(), request.getDate()) < 1;
    }

    private int calculateNumberOfDaysBetween(Date startDate, Date endDate) {
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("End date should be grater or equals to start date");
        }

        long startDateTime = startDate.getTime();
        long endDateTime = endDate.getTime();
        long milPerDay = 1000*60*60*24;

        return (int) ((endDateTime - startDateTime) / milPerDay);
    }

    public void delete(int bannerId) {
        Banner banner = bannerRepository.
                findById(bannerId).
                orElseThrow(() -> new IllegalArgumentException("Banner with id " + bannerId + " not find"));

        banner.setDeleted(true);

        bannerRepository.save(banner);
    }
}
