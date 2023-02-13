package me.ver.Authserver7.config;

import lombok.RequiredArgsConstructor;
import me.ver.Authserver7.domain.Authority;
import me.ver.Authserver7.domain.AuthorityEnum;
import me.ver.Authserver7.repository.AuthorityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * INSERT INTO authority (AUTHORITY_STATUS) values ('ROLE_USER');
 * INSERT INTO authority (AUTHORITY_STATUS) values ('ROLE_ADMIN');
 */
@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final AuthorityRepository authorityRepository;

        public void dbInit() {
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_ADMIN));
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_USER));
        }
    }
}


