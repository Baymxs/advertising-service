package ru.nsu.bayramov.advertisingservice.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.bayramov.advertisingservice.model.domain.Banner;
import ru.nsu.bayramov.advertisingservice.model.domain.Request;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    Request findByBannerAndUserAgentAndAndIpAddress(Banner banner, String userAgent, String ipAddress);
}
