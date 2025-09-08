package com.inditex.prices_service.infrastructure.config;

import com.inditex.prices_service.infrastructure.db.SpringDataPriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.CommandLineRunner;

import static org.mockito.Mockito.*;

class DataInitializerTest {
    @Test
    void initDataShouldSaveEntities() throws Exception {
        SpringDataPriceRepository repository = Mockito.mock(SpringDataPriceRepository.class);
        DataInitializer initializer = new DataInitializer();
        CommandLineRunner runner = initializer.initData(repository);
        runner.run(new String[]{});
        verify(repository, times(4)).save(any());
    }
}

