package me.ver.Authserver7.service;

import me.ver.Authserver7.domain.User;
import me.ver.Authserver7.dto.UserRequestDto;
import me.ver.Authserver7.dto.TokenDto;
import me.ver.Authserver7.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        UserRequestDto dto = new UserRequestDto();
        dto.setEmail("test@test.com");
        dto.setPassword("54321");

        //when
        authService.signup(dto);
        // 영속성 컨텍스트 플러쉬
        em.flush();
        em.clear();

        //then
        Optional<User> byEmail = userRepository.findByEmail("test@test.com");
        // dto의 이메일, repository에서 찾은 이메일
        assertEquals(dto.getEmail(), byEmail.get().getEmail());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        UserRequestDto dto1 = new UserRequestDto();
        dto1.setEmail("User@User.com");
        dto1.setPassword("1234");

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setEmail("User@User.com");
        dto2.setPassword("1234");

        //when
        authService.signup(dto1);

        try {
            authService.signup(dto2);
        } catch (RuntimeException e) {
            return;
        }

        //then
        fail("예외가 발생해야 합니다.");
    }

    @Test
    public void 로그인() throws Exception {
        //given
        UserRequestDto dto1 = new UserRequestDto();
        dto1.setEmail("User@User.com");
        dto1.setPassword("1234");
        authService.signup(dto1);

        // 영속성 컨텍스트 플러쉬
        em.flush();
        em.clear();

        UserRequestDto dto2 = new UserRequestDto();
        dto2.setEmail("User@User.com");
        dto2.setPassword("1234");

        //when
        TokenDto login = authService.login(dto2);

        //then
        assertEquals(login.getGrantType(), "bearer");
    }
}