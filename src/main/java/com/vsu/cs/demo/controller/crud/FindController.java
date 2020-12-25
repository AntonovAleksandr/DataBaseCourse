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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/app")
public class FindController {
    public final SummaryService summaryService;
    public final TechnologiesService technologiesService;
    public final UserViewService userViewService;
    public final UserService userService;
    public final ItemsService itemsService;
    public final VacancyService vacancyService;
    public final SqlQueryDao statistic;

    public FindController(SummaryService summaryService, TechnologiesService technologiesService, UserViewService userViewService, UserService userService, ItemsService itemsService, VacancyService vacancyService, SqlQueryDao statistic) {
        this.summaryService = summaryService;
        this.technologiesService = technologiesService;
        this.userViewService = userViewService;
        this.userService = userService;
        this.itemsService = itemsService;
        this.vacancyService = vacancyService;
        this.statistic = statistic;
    }

    @RequestMapping(value = "/users/findBy/login", method = RequestMethod.POST)
    public ModelAndView usersFindLogin(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users.add(userViewService.findByLogin(target));
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/findBy/name", method = RequestMethod.POST)
    public ModelAndView usersFindName(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");

        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users = userViewService.findAllByValueAndItem(target, "name");
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/findBy/surname", method = RequestMethod.POST)
    public ModelAndView usersFindSurname(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users = userViewService.findAllByValueAndItem(target, "surname");
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/findBy/fatherName", method = RequestMethod.POST)
    public ModelAndView usersFindFathername(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users = userViewService.findAllByValueAndItem(target, "father name");
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/findBy/phoneNumber", method = RequestMethod.POST)
    public ModelAndView usersFindFhoneNumber(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users = userViewService.findAllByValueAndItem(target, "phone number");
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/users/findBy/email", method = RequestMethod.POST)
    public ModelAndView usersFindEmail(@RequestParam("target") String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users = userViewService.findAllByValueAndItem(target, "email");
        } else users = userViewService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.addObject("items", itemsDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }


    @RequestMapping(value = "/summaries/findBy/employee", method = RequestMethod.POST)
    public ModelAndView summariesFindEmployee(@RequestParam String target) {
        List<ItemsDto> itemsDtos = itemsService.findAll();
        List<UserViewDto> users = new ArrayList<>();
        if (target != null && !target.equals("")) {
            users.add(userViewService.findByLogin(target));
        } else users = userViewService.findAll();

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

    @RequestMapping(value = "/summaries/findBy/vacancy", method = RequestMethod.POST)
    public ModelAndView summariesFindVacancy(@RequestParam String target) {
        summaryService.findByVacancy(target);

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

    @RequestMapping(value = "/summaries/findBy/company", method = RequestMethod.POST)
    public ModelAndView summariesFindCompany(@RequestParam String target) {
        List<SummaryViewDto> summaries;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("summaries");
        if (target != null && !target.equals("")) {
            List<VacancyDto>vacancyDtos = vacancyService.findAllWhereCompanyIs(target);
            summaries = new ArrayList<>();
            for (var dto: vacancyDtos) {
                summaries.addAll(SummaryMapper.INSTANCE.dtoToView(summaryService.findAllByCompany(dto.getId())));
                summaries.forEach(summaryDto -> {
                    VacancyDto vacancyDto = vacancyService.findById(summaryDto.getDesiredVacancyId());
                    UserViewDto userViewDto = userViewService.findById(summaryDto.getEmployeeId());
                    summaryDto.setCompany(vacancyDto.getCompany());
                    summaryDto.setTitle(vacancyDto.getTitle());
                    summaryDto.setEmployee(userViewDto);

                });
            }
        } else {
            summaries = SummaryMapper.INSTANCE.dtoToView(summaryService.findAll());
            summaries.forEach(summaryDto -> {
                VacancyDto vacancyDto = vacancyService.findById(summaryDto.getDesiredVacancyId());
                UserViewDto userViewDto = userViewService.findById(summaryDto.getEmployeeId());
                summaryDto.setCompany(vacancyDto.getCompany());
                summaryDto.setTitle(vacancyDto.getTitle());
                summaryDto.setEmployee(userViewDto);

            });
        }
        modelAndView.addObject("summaries", summaries);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }


    @RequestMapping(value = "/technologies/findBy/employee", method = RequestMethod.POST)
    public ModelAndView technologiesFindEmployee(@RequestParam String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("technologies");
        List<TechnologiesViewDto> technologies;
        if(target!=null && !target.equals("")){
            UserViewDto user = userViewService.findByLogin(target);
            if(user != null){ technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAllByEmployee(user.getId()));}
            else
                technologies = new ArrayList<>();
        } else {
            technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAll());
        }
        technologies.forEach(technologiesDto -> technologiesDto.setPerson(userViewService.findById(technologiesDto.getPersonId())));
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<StatisticDto> allStatisticDtos  = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        modelAndView.addObject("technologies", technologies);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/technologies/findBy/title", method = RequestMethod.POST)
    public ModelAndView technologiesFindTitle(@RequestParam String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("technologies");
        List<TechnologiesViewDto> technologies;
        if(target!=null && !target.equals("")){
            technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAllByTitle(target));
        } else {
            technologies = TechnologiesMapper.INSTANCE.technologiesToView(technologiesService.findAll());
        }
        technologies.forEach(technologiesDto -> technologiesDto.setPerson(userViewService.findById(technologiesDto.getPersonId())));
        List<StatisticDto> statisticDtos = statistic.getThreeMostPopularTechnologies();
        modelAndView.addObject("statistic", statisticDtos);
        List<StatisticDto> allStatisticDtos = statistic.getTopAllTechnologies();
        modelAndView.addObject("allStatistic", allStatisticDtos);
        modelAndView.addObject("technologies", technologies);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }



    @RequestMapping(value = "/vacancies/findBy/company", method = RequestMethod.POST)
    public ModelAndView vacanciesFindCompany(@RequestParam String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");


        List<VacancyViewDto> vacancyDtos;

        if (target != null && !target.equals("")) {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAllWhereCompanyIs(target));
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        } else {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        }
        vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/vacancies/findBy/employer", method = RequestMethod.POST)
    public ModelAndView vacanciesFindEmployer(@RequestParam String target) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");
        UserViewDto user = userViewService.findByLogin(target);
        List<VacancyViewDto> vacancyDtos;
        if (user != null) {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAllWhereEmployerIdIs(user.getId()));
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(user));
        } else {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(user));
        }
        if (vacancyDtos.isEmpty()) {
            modelAndView.addObject("empty", true);
        }
        modelAndView.addObject("empty", false);
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }

    @RequestMapping(value = "/vacancies/findBy/vacancy", method = RequestMethod.POST)
    public ModelAndView vacanciesFindVacancy(@RequestParam String target) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("vacancies");
        List<VacancyViewDto> vacancyDtos;
        if (target != null && !target.equals("")) {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAllWhereTitleIs(target));
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        } else {
            vacancyDtos = VacancyMapper.INSTANCE.dtoToView(vacancyService.findAll());
            vacancyDtos.forEach(vacancyViewDto -> vacancyViewDto.setEmployer(UserMapper.INSTANCE.dtoToView(userService.findByID(vacancyViewDto.getEmployerId()))));
        }
        modelAndView.addObject("vacancies", vacancyDtos);
        modelAndView.addObject("exeption", false);
        return modelAndView;
    }
}
