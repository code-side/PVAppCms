package com.codeside.pvapp.cucumber.stepdefs;

import com.codeside.pvapp.PvAppCmsApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = PvAppCmsApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
