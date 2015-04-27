package com.crdmix.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crdmix.bdd.configuration.FakeConsole;

@Component
public class WriteCommandSteps {

    @Autowired
    private FakeConsole captureConsole;
    private ArrayList<String> lastConsoleOutput;

    @When("$1 posts message \"$2\"")
    @Given("$1 has posted message \"$2\"")
    public void whenUserPostsMessage(String userName, String message) {
        String commandLine = userName + " -> " + message;
        captureConsole.incomingLineToStdIn(commandLine);
    }

    @When("$1 reads {his|her} timeline")
    public void readTimeline(String username) throws UnsupportedEncodingException, InterruptedException {
        captureConsole.incomingLineToStdIn(username);
        this.lastConsoleOutput = new ArrayList<>(captureConsole.getScreen());
    }

    @When("$1 reads the timeline of $")
    public void readSomeOneElsesTimeLine(String user, String userTimeLineToRead) throws UnsupportedEncodingException,
            InterruptedException {
        readTimeline(userTimeLineToRead);
    }

    @Then("the last output contains \"$1\"")
    public void checkOutput(String expectedLine) {
        assertThat(this.lastConsoleOutput).contains(expectedLine);
    }

    @Given("$1 $2 have passed")
    public void givenTimeHavePassed(int time, String units) {
        long currentOffSet = DateTimeUtils.currentTimeMillis() - System.currentTimeMillis();
        long desiredRelativeOffSet = TimeUnit.MILLISECONDS.convert(time, TimeUnit.valueOf(units.toUpperCase()));
        DateTimeUtils.setCurrentMillisOffset(currentOffSet + desiredRelativeOffSet);
    }

    @Given("$1 follows $2")
    public void givenUserFollowsAnother(String user, String followsUser) {
        String commandLine = user + " follows " + followsUser;
        captureConsole.incomingLineToStdIn(commandLine);
    }

    @When("wall of $1 is read")
    public void whenWallIsRead(String user) {
        String commandLine = user + " wall";
        captureConsole.incomingLineToStdIn(commandLine);
        this.lastConsoleOutput = new ArrayList<>(captureConsole.getScreen());
    }
}
