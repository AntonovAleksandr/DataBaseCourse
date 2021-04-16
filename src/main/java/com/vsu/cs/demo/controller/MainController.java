package com.vsu.cs.demo.controller;

import com.vsu.cs.demo.data.dto.*;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.dto.view.VacancyViewDto;
import com.vsu.cs.demo.data.mapper.SummaryMapper;
import com.vsu.cs.demo.data.mapper.TechnologiesMapper;
import com.vsu.cs.demo.data.mapper.UserMapper;
import com.vsu.cs.demo.data.mapper.VacancyMapper;
import com.vsu.cs.demo.data.repository.SqlQueryDao;
import com.vsu.cs.demo.data.repository.VacancyRepository;
import com.vsu.cs.demo.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MainController {
    public final SummaryService summaryService;
    public final TechnologiesService technologiesService;
    public final UserViewService userViewService;
    public final UserService userService;
    public final ItemsService itemsService;
    public final VacancyRepository vacancyRepository;
    public final VacancyService vacancyService;
    public final SqlQueryDao statistic;



    public MainController(SummaryService summaryService, TechnologiesService technologiesService, UserViewService userViewService, UserService userService, ItemsService itemsService, VacancyRepository vacancyRepository, VacancyService vacancyService, SqlQueryDao statistic) {
        this.summaryService = summaryService;
        this.technologiesService = technologiesService;
        this.userViewService = userViewService;
        this.userService = userService;
        this.itemsService = itemsService;
        this.vacancyRepository = vacancyRepository;
        this.vacancyService = vacancyService;
        this.statistic = statistic;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("summaries");
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


    @RequestMapping(value = "/vacancies", method = RequestMethod.GET)
    public ModelAndView vacancies() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");
        List<VacancyViewDto> vacancyDtos = VacancyMapper.INSTANCE.vacancyToView(vacancyRepository.findAll());
        vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        if (vacancyDtos.isEmpty()){
            modelAndView.addObject("empty", true);
        }
        modelAndView.addObject("empty", false);
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public ModelAndView errors() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errors");
        modelAndView.addObject("empty", false);
        return modelAndView;
    }

    @RequestMapping(value = "/technologies", method = RequestMethod.GET)
    public ModelAndView technologies() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("technologies");
        List<TechnologiesViewDto> technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAll());
        technologies.forEach(technologiesDto -> technologiesDto.setPerson(userViewService.findById(technologiesDto.getPersonId())));
        modelAndView.addObject("technologies", technologies);
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<StatisticDto> allStatisticDtos  = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }
    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public ModelAndView statistic() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("statistic");
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<StatisticDto> allStatisticDtos  = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ModelAndView items() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items");
        List<ItemsDto> items = itemsService.findAll();
        modelAndView.addObject("items", items);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }
}
