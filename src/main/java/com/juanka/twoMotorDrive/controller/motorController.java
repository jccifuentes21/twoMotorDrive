package com.juanka.twoMotorDrive.com.juanka.twoMotorDrive.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class motorController {

    @RequestMapping("/")
    public String start () {

        return "app is running ok.";
    }

    @RequestMapping("/drive")
    public String drive () throws InterruptedException{

        GpioController gpio = GpioFactory.getInstance();

        GpioPinDigitalOutput leftMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Motor Izquierda", PinState.LOW);
        GpioPinDigitalOutput rightMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Motor Derecha", PinState.LOW);


        leftMotor.high();
        rightMotor.high();
        Thread.sleep(3000);

        gpio.shutdown();
        gpio.unprovisionPin(leftMotor);
        gpio.unprovisionPin(rightMotor);

        System.out.println("Motors worked");

        return "Program was executed correctly";

    }
}
