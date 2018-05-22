package com.mink.demo.bowlingapi.api.rest;

import com.google.common.collect.Lists;
import com.mink.demo.bowlingapi.api.rest.exceptions.RestExceptionHandler;
import com.mink.demo.bowlingapi.service.BowlerService;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import factory.data.BowlerData;
import factory.util.JsonUtil;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
public class BowlerResourceTest {

    private MockMvc mvc;

    @Mock
    private BowlerService bowlerService;

    @InjectMocks
    private BowlerResource bowlerResource;

    private BowlerResponse testBowlerResponse;

    @Before
    public void setup() {

        mvc = MockMvcBuilders.standaloneSetup(bowlerResource)
                .setControllerAdvice(new RestExceptionHandler())
                .setValidator(new LocalValidatorFactoryBean())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

        testBowlerResponse = BowlerData.getRandomBowlerResponse();
    }

    @Test
    public void getBowlersByGameId() throws Exception {

        // Setup mock return payload
        Set<BowlerResponse> bowlerResponses = Sets.newLinkedHashSet(testBowlerResponse);

        // Mock service response
        willReturn(bowlerResponses)
                .given(bowlerService).getBowlersByGameId(any(Long.class));

        // Make request
        MockHttpServletResponse response = mvc.perform(
                get("/v1/game/1/bowler")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verify correct method is called and has correct parameters
        then(bowlerService).should().getBowlersByGameId(eq(1L));

        // Verify correct status code and content
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(JsonUtil.toJson(Lists.newArrayList(testBowlerResponse).toArray()));


    }

    @Test
    public void addBowlerToGame() throws Exception {

        // Mock service response
        willReturn(Sets.newLinkedHashSet(testBowlerResponse))
                .given(bowlerService).addBowlerToGame(any(Long.class), any(BowlerRequest.class));

        // Make request
        MockHttpServletResponse response = mvc.perform(
                post("/v1/game/1/bowler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testBowlerResponse)))
                .andReturn()
                .getResponse();

        // Verify correct method is called and has correct parameters
        then(bowlerService).should().addBowlerToGame(eq(1L), any(BowlerRequest.class));

        // Verify correct status code and content
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void getBowlerByGameIdById() throws Exception {
        // Setup mock return payload

        // Mock service response
        willReturn(testBowlerResponse)
                .given(bowlerService).getBowlerByGameIdById(any(Long.class), any(Long.class));

        // Make request
        MockHttpServletResponse response = mvc.perform(
                get("/v1/game/1/bowler/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Verify correct method is called and has correct parameters
        then(bowlerService).should().getBowlerByGameIdById(eq(1L), eq(2L));

        // Verify correct status code and content
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(JsonUtil.toJson(testBowlerResponse));
    }

    @Test
    public void deleteBowlerByGameIdById() throws Exception {

        // Make request
        MockHttpServletResponse response = mvc.perform(
                delete("/v1/game/1/bowler/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(testBowlerResponse)))
                .andReturn()
                .getResponse();

        // Verify correct method is called and has correct parameters
        then(bowlerService).should().deleteBowlerByGameIdById(eq(1L), eq(2L));

        // Verify correct status code
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
        
    }
}