package com.PROGI.backend.exceptions;

import com.PROGI.backend.model.Profile;

public class ProfileSearchEmpty extends Exception{
    public ProfileSearchEmpty() { super("No such profile exists"); }
}
