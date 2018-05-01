package com.example.enoch.makeapp.ui.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by mainza1992 on 5/8/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
