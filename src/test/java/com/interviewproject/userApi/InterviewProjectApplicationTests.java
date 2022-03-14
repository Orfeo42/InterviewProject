package com.interviewproject.userApi;

import com.interviewproject.userApi.api.exception.ApiRequestException;
import com.interviewproject.userApi.api.user.UserRequest;
import com.interviewproject.userApi.dto.user.User;
import com.interviewproject.userApi.dto.user.UserRepository;
import com.interviewproject.userApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InterviewProjectApplicationTests {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void getUsersTest() {
        List<User> usrList = new ArrayList<>();
        for (int i = 1; i<5; i++) {
            usrList.add(
                new User(
                    1L,
                    "Nome"+ i,
                    "Cognome"+ i,
                    "nome" + i + "@email.it",
                    "password"+ i,
                    "Address"+ i
                )
            );
        }
        UserRequest request = new UserRequest();
        Page<User> userPage = new PageImpl(usrList);
        Mockito.when(repository.findAll()).thenReturn(usrList);
        assertEquals(4, service.getAll().size());
    }
    @Test
    void testCreate() throws ApiRequestException {

    }

    @Test
    void readAll() {

    }

}
