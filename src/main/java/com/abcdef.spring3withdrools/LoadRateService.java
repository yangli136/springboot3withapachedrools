package com.iddsoft.springndrools.service;

import com.iddsoft.springndrools.model.Participant;
import com.iddsoft.springndrools.model.Rate;

public interface LoadRateService {

	Rate getRate(Participant applicantRequest);

}
