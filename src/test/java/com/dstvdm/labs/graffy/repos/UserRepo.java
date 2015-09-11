package com.dstvdm.labs.graffy.repos;

import com.dstvdm.labs.graffy.GraffyConfig;
import com.dstvdm.labs.graffy.Repository;
import com.google.inject.Inject;

/**
 * A repository for handling user-related database manipulations.
 */
public class UserRepo extends Repository {

  @Inject
  public UserRepo(GraffyConfig config) {
    super(config);
  }

}
