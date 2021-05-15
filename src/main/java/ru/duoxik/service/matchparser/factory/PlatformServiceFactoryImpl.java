package ru.duoxik.service.matchparser.factory;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.duoxik.entity.Platform;
import ru.duoxik.service.matchparser.PlatformService;

@Service
public class PlatformServiceFactoryImpl implements PlatformServiceFactory {

    private final PlatformService fastcupService;

    public PlatformServiceFactoryImpl(@Qualifier("fastcupService") PlatformService fastcupService) {
        this.fastcupService = fastcupService;
    }

    @Override
    public PlatformService getService(@NonNull Platform platform) {
        switch (platform) {
            case FAST_CUP:
                return fastcupService;
            default:
                throw new IllegalArgumentException("Not found parser service for " + platform);
        }
    }
}
