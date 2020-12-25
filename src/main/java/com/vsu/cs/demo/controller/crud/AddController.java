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
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class AddController {
    public final SummaryService summaryService;
    public final TechnologiesService technologiesService;
    public final UserViewService userViewService;
    public final UserService userService;
    public final ItemsService itemsService;
    public final VacancyService vacancyService;
    public final ValuesOfFormsRepository valuesOfFormsRepository;
    public final SqlQueryDao statistic;

    public AddController(SummaryService summaryService, TechnologiesService technologiesService, UserViewService userViewService, UserService userService, ItemsService itemsService, VacancyService vacancyService, ValuesOfFormsRepository valuesOfFormsRepository, SqlQueryDao statistic) {
        this.summaryService = summaryService;
        this.technologiesService = technologiesService;
        this.userViewService = userViewService;
        this.userService = userService;
        this.itemsService = itemsService;
        this.vacancyService = vacancyService;
        this.valuesOfFormsRepository = valuesOfFormsRepository;
        this.statistic = statistic;
    }
    @SneakyThrows
    @RequestMapping(value = "/summaries/add", method = RequestMethod.POST)
    public ModelAndView summariesAdd(
            @RequestParam("employee") String employee,
            @RequestParam("vacancy") String vacancy,
            @RequestParam("сompany") String сompany,
            @RequestParam("date")  LocalDate date
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("summaries");
        UserViewDto userViewDto = userViewService.findByLogin(employee);
        VacancyDto vacancyDto = vacancyService.findByTitleAndCompany(vacancy, сompany);
        MassageDto errorMassage = new MassageDto();
        if (userViewDto != null && vacancyDto != null) {
            summaryService.save(new SummaryViewDto(сompany, userViewDto, vacancy, date), vacancyDto);
            modelAndView.addObject("exeption", false);
        } else if (userViewDto == null) {
            modelAndView.addObject("exeption", true);
            errorMassage.setText("404, this login is nit found.");
            modelAndView.addObject("massage", errorMassage);

        } else {
            modelAndView.addObject("exeption", true);
            errorMassage.setText("404, this vacancy is nit found.");
            modelAndView.addObject("massage", errorMassage);
        }

        List<SummaryDto> summaries = summaryService.findAll();
        List<SummaryViewDto> summaryViewDtos = new ArrayList<>();
        summaries.forEach(summaryDto -> {
            SummaryViewDto dto = SummaryMapper.INSTANCE.dtoToView(summaryDto);
            VacancyDto dto1 = vacancyService.findById(dto.getDesiredVacancyId());
            dto.setEmployee(userViewService.findById(dto.getEmployeeId()));
            dto.setTitle(dto1.getTitle());
            dto.setCompany(dto1.getCompany());
            summaryViewDtos.add(dto);
        });

        modelAndView.addObject("summaries", summaryViewDtos);
        return modelAndView;
    }

    @RequestMapping(value = "/vacancies/add", method = RequestMethod.POST)
    public ModelAndView vacanciesAdd(
            @RequestParam("company") String company,
            @RequestParam("title") String title,
            @RequestParam("login") String login


    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");

        UserViewDto userViewDto = userViewService.findByLogin(login);
        MassageDto errorMassage = new MassageDto();
        if (userViewDto != null) {
            vacancyService.save(new VacancyDto(userViewDto.getId(), title, company));
            modelAndView.addObject("exeption", false);
        } else {
            modelAndView.addObject("exeption", true);
            errorMassage.setText("404, this login is nit found.");
            modelAndView.addObject("massage", errorMassage);

        }

        List<VacancyViewDto> vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
        vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        modelAndView.addObject("vacancies", vacancyDtos);
        return modelAndView;
    }

    @PostMapping(value = "/technologies/add")
    public ModelAndView technologiesAdd(
            @RequestParam String login,
            @RequestParam String title,
            @RequestParam LocalDate date
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("technologies");

        UserViewDto userViewDto = userViewService.findByLogin(login);
        MassageDto errorMassage = new MassageDto();
        if (userViewDto != null) {
            technologiesService.save(new TechnologiesDto(userViewDto.getId(), title, date));
            modelAndView.addObject("exeption", false);
        } else {
            modelAndView.addObject("exeption", true);
            errorMassage.setText("404, this login is nit found.");
            modelAndView.addObject("massage", errorMassage);

        }
        List<StatisticDto> allStatisticDtos  = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<TechnologiesViewDto> technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAll());
        technologies.forEach(technologiesDto -> technologiesDto.setPerson(userViewService.findById(technologiesDto.getPersonId())));
        modelAndView.addObject("technologies", technologies);
        return modelAndView;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ModelAndView usersAdd(
            @RequestParam String login,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("father name" )String fatherName,
            @RequestParam("phone number") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam LocalDate date
    ) {
        userService.save(new SecurityUserDto(login, password));
        UserViewDto userViewDto = userViewService.findByLogin(login);
        List<ValuesDto> valuesDtos = new ArrayList<>();
        valuesDtos.add(new ValuesDto(name, 1L, userViewDto.getId(), date));
        valuesDtos.add(new ValuesDto(surname, 2L, userViewDto.getId(), date));
        valuesDtos.add(new ValuesDto(fatherName, 3L, userViewDto.getId(), date));
        valuesDtos.add(new ValuesDto(phoneNumber, 4L, userViewDto.getId(), date));
        valuesDtos.add(new ValuesDto(email, 5L, userViewDto.getId(), date));
        valuesOfFormsRepository.saveAll(ValuesMapper.INSTANCE.dtoToValue(valuesDtos));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        return modelAndView;
    }


    @RequestMapping(value = "/items/add", method = RequestMethod.POST)
    public ModelAndView itemsAdd(@RequestParam String value) {
        itemsService.save(new ItemsDto(value));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items");
        List<ItemsDto> items = itemsService.findAll();
        modelAndView.addObject("items", items);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }
}
