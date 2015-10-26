package com.juanka.twoMotorDrive.controller;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class motorController {

    GpioController gpio = GpioFactory.getInstance();

    GpioPinDigitalOutput leftBackMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Motor Izquierda", PinState.LOW);
    GpioPinDigitalOutput rightBackMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Motor Derecha", PinState.LOW);
    GpioPinDigitalOutput rightFrontMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Motor Derecha", PinState.LOW);
    GpioPinDigitalOutput leftFrontMotor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Motor Derecha", PinState.LOW);

    @RequestMapping("/index.html")
    public String start () {

        return "app is running ok.";
    }

    @RequestMapping("/forward.html")
    public String forward () throws InterruptedException{

        leftFrontMotor.high();
        rightFrontMotor.high();

        return "Moving forward...";

    }

    @RequestMapping("/back.html")
    public String back () throws InterruptedException{

        leftBackMotor.high();
        rightBackMotor.high();

        return "Moving backwards...";

    }

    @RequestMapping("/right.html")
    public String right () throws InterruptedException{

        leftFrontMotor.toggle();
        rightFrontMotor.toggle();

        return "Moving right...";

    }

    @RequestMapping("/left.html")
    public String left () throws InterruptedException{

        leftFrontMotor.toggle();
        rightFrontMotor.toggle();

        return "Moving left...";

    }

    @RequestMapping("/shutdown.html")
    public String shutdown () {

        gpio.shutdown();
        gpio.unprovisionPin(leftBackMotor);
        gpio.unprovisionPin(rightBackMotor);
        gpio.unprovisionPin(rightFrontMotor);
        gpio.unprovisionPin(leftFrontMotor);

        return "Program was shutdown correctly";
    }
}
