package com.vsu.cs.demo.controller.crud;

import com.vsu.cs.demo.data.dto.ItemsDto;
import com.vsu.cs.demo.data.dto.StatisticDto;
import com.vsu.cs.demo.data.dto.VacancyDto;
import com.vsu.cs.demo.data.dto.view.SummaryViewDto;
import com.vsu.cs.demo.data.dto.view.TechnologiesViewDto;
import com.vsu.cs.demo.data.dto.view.UserViewDto;
import com.vsu.cs.demo.data.dto.view.VacancyViewDto;
import com.vsu.cs.demo.data.mapper.SummaryMapper;
import com.vsu.cs.demo.data.mapper.TechnologiesMapper;
import com.vsu.cs.demo.data.mapper.UserMapper;
import com.vsu.cs.demo.data.mapper.VacancyMapper;
import com.vsu.cs.demo.data.repository.SqlQueryDao;
import com.vsu.cs.demo.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/app")
public class DeleteController {
    public final SummaryService summaryService;
    public final TechnologiesService technologiesService;
    public final UserViewService userViewService;
    public final UserService userService;
    public final ItemsService itemsService;
    public final VacancyService vacancyService;
    public final SqlQueryDao statistic;

    public DeleteController(SummaryService summaryService, TechnologiesService technologiesService, UserViewService userViewService, UserService userService, ItemsService itemsService, VacancyService vacancyService, SqlQueryDao statistic) {
        this.summaryService = summaryService;
        this.technologiesService = technologiesService;
        this.userViewService = userViewService;
        this.userService = userService;
        this.itemsService = itemsService;
        this.vacancyService = vacancyService;
        this.statistic = statistic;
    }

    @RequestMapping(value = "/summaries/delete", method = RequestMethod.POST)
    public ModelAndView summariesDelete(@RequestParam Long id) {

        summaryService.deleteById(id);

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


    @RequestMapping(value = "/technologies/delete", method = RequestMethod.POST)
    public ModelAndView technologiesDelete(@RequestParam Long id) {
        technologiesService.deleteById(id);

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

    @RequestMapping(value = "/items/delete", method = RequestMethod.POST)
    public ModelAndView itemsDelete(@RequestParam Long id) {
        itemsService.deleteById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("items");
        List<ItemsDto> items = itemsService.findAll();
        modelAndView.addObject("items", items);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }


    @RequestMapping(value = "/vacancies/delete", method = RequestMethod.POST)
    public ModelAndView vacanciesDelete(@RequestParam Long id) {
        vacancyService.deleteById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");
        List<VacancyViewDto> vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
        vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

}
