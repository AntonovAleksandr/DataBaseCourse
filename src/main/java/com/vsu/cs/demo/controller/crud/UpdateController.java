package com.vsu.cs.demo.controller.crud;

import com.vsu.cs.demo.data.dto.*;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.dto.view.VacancyViewDto;
import com.vsu.cs.demo.data.mapper.*;
import com.vsu.cs.demo.data.repository.SqlQueryDao;
import com.vsu.cs.demo.data.repository.ValuesOfFormsRepository;
import com.vsu.cs.demo.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class UpdateController {
    public final SummaryService summaryService;
    public final TechnologiesService technologiesService;
    public final UserViewService userViewService;
    public final UserService userService;
    public final ItemsService itemsService;
    public final VacancyService vacancyService;
    public final SqlQueryDao statistic;
    public final ValuesOfFormsRepository valuesOfFormsRepository;

    public UpdateController(SummaryService summaryService, TechnologiesService technologiesService, UserViewService userViewService, UserService userService, ItemsService itemsService, VacancyService vacancyService, SqlQueryDao statistic, ValuesOfFormsRepository valuesOfFormsRepository) {
        this.summaryService = summaryService;
        this.technologiesService = technologiesService;
        this.userViewService = userViewService;
        this.userService = userService;
        this.itemsService = itemsService;
        this.vacancyService = vacancyService;
        this.statistic = statistic;
        this.valuesOfFormsRepository = valuesOfFormsRepository;
    }

    @RequestMapping(value = "/items/update", method = RequestMethod.POST)
    public ModelAndView itemUpdate(@RequestParam Long id) {
        itemsService.deleteById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items");
        List<ItemsDto> items = itemsService.findAll();
        modelAndView.addObject("items", items);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }


    @RequestMapping(value = "/vacancies/update", method = RequestMethod.POST)
    public ModelAndView vacanciesUpdate(@RequestParam String company,
                                        @RequestParam String title,
                                        @RequestParam String login,
                                        @RequestParam Long id) {
        UserViewDto userViewDto = userViewService.findByLogin(login);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");
        if (userViewDto!=null) {
            vacancyService.update(new VacancyDto(id, userViewDto.getId(), title, company));
        } else {
            MassageDto errorMassage = new MassageDto("404, this login is not found");
            modelAndView.addObject("massage", errorMassage);
            modelAndView.addObject("exeption", true);
        }
        List<VacancyViewDto> vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
        vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        if (vacancyDtos.isEmpty()){
            modelAndView.addObject("empty", true);
        }
        modelAndView.addObject("empty", false);
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/summaries/update", method = RequestMethod.POST)
    public ModelAndView summariesUpdate(@RequestParam String login,
                                        @RequestParam String title,
                                        @RequestParam String company,
                                        @RequestParam Long id) {
        UserViewDto uDto = userViewService.findByLogin(login);
        VacancyDto vDto = vacancyService.findByTitleAndCompany(title, company);
        MassageDto errorMassage = new MassageDto();


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("summaries");
        if (uDto!=null && vDto!=null) {
            SummaryDto  summaryDto = new SummaryDto(id, uDto.getId(), vDto.getId(), LocalDate.now());
            summaryService.update(summaryDto);
        } else {
            if (uDto!=null) {
                errorMassage = new MassageDto("404, this login is not found");
                modelAndView.addObject("exeption", true);
                modelAndView.addObject("massage", errorMassage);
            } else {
                errorMassage = new MassageDto("404, this vacancy is not found");
                modelAndView.addObject("exeption", true);
                modelAndView.addObject("massage", errorMassage);
            }
        }
        List<SummaryViewDto> summaries = SummaryMapper.INSTANCE.dtoToView(summaryService.findAll());
        summaries.forEach(summaryDto -> {
            VacancyDto vacancyDto = vacancyService.findById(summaryDto.getDesiredVacancyId());
            UserViewDto userViewDto = userViewService.findById(summaryDto.getEmployeeId());
            summaryDto.setCompany(vacancyDto.getCompany());
            summaryDto.setTitle(vacancyDto.getTitle());
            summaryDto.setEmployee(userViewDto);

        });

        modelAndView.addObject("summaries", summaries);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }


    @RequestMapping(value = "/technologies/update", method = RequestMethod.POST)
    public ModelAndView technologiesUpdate(@RequestParam Long id) {
//        technologiesService.update(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("technologies");
        List<TechnologiesViewDto> technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAll());
        technologies.forEach(technologiesDto -> technologiesDto.setPerson(userViewService.findById(technologiesDto.getPersonId())));
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<StatisticDto> allStatisticDtos  = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        modelAndView.addObject("technologies", technologies);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView usersUpdate(
            @RequestParam ("oldLogin") String oldLogin,
            @RequestParam ("login")String login,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("father name" )String fatherName,
            @RequestParam("phone number") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam ("date") LocalDate date
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        UserViewDto oldUser = userViewService.findByLogin(oldLogin);
        if (userService.update(new SecurityUserDto(oldUser.getId(), login, password))) {
            UserViewDto userViewDto = userViewService.findByLogin(login);
            List<ValuesDto> valuesDtos = new ArrayList<>();
            valuesDtos.add(new ValuesDto(name, 1L, userViewDto.getId(), date));
            valuesDtos.add(new ValuesDto(surname, 2L, userViewDto.getId(), date));
            valuesDtos.add(new ValuesDto(fatherName, 3L, userViewDto.getId(), date));
            valuesDtos.add(new ValuesDto(phoneNumber, 4L, userViewDto.getId(), date));
            valuesDtos.add(new ValuesDto(email, 5L, userViewDto.getId(), date));
            valuesOfFormsRepository.saveAll(ValuesMapper.INSTANCE.dtoToValue(valuesDtos));
        }
        else {
            MassageDto errorMassage = new MassageDto("invalid password!");
            modelAndView.addObject("exeption", true);
            modelAndView.addObject("massage", errorMassage);
        }


        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        return modelAndView;
    }
}
