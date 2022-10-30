package com.mycompany.myapp.authentication.domain;

import com.mycompany.myapp.common.domain.DummyAppCollections;
import com.mycompany.myapp.error.domain.Assert;
import java.util.Set;
import java.util.stream.Stream;

public record Roles(Set<Role> roles) {

  public static final Roles EMPTY = new Roles(null);
  
  public Roles(Set<Role> roles) {
    this.roles = DummyAppCollections.immutable(roles);
  }

  public boolean hasRole() {
    return !roles.isEmpty();
  }

  public boolean hasRole(Role role) {
    Assert.notNull("role", role);

    return roles.contains(role);
  }
  
  public Stream<Role> stream() {
    return get().stream();
  }

  public Set<Role> get() {
    return roles();
  }
}
