/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.security.privilege.rest;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

import org.sonatype.nexus.security.internal.rest.NexusSecurityApiConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @since 3.next
 */
@Api(value = "Security Management: Privileges")
public interface PrivilegeApiResourceDoc
{
  @ApiOperation("Retrieve a list of privileges.")
  @ApiResponses(value = {
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS)
  })
  Collection<ApiPrivilege> getPrivileges();

  @ApiOperation("Retrieve a privilege by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS),
      @ApiResponse(code = 404, message = NexusSecurityApiConstants.PRIVILEGE_NOT_FOUND)
  })
  ApiPrivilege getPrivilege(@ApiParam("The id of the privilege to retrieve.") @NotNull final String id);

  @ApiOperation("Delete a privilege by id.")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = NexusSecurityApiConstants.PRIVILEGE_READ_ONLY),
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS),
      @ApiResponse(code = 404, message = NexusSecurityApiConstants.PRIVILEGE_NOT_FOUND)
  })
  void deletePrivilege(@ApiParam("The id of the privilege to delete.") @NotNull final String id);

  @ApiOperation("Create an application type privilege.")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = NexusSecurityApiConstants.PRIVILEGE_MISCONFIGURED),
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS)
  })
  Response createPrivilege(@ApiParam(
      "The privilege to create.") @NotNull @Valid final ApiPrivilegeApplicationRequest privilege);

  @ApiOperation("Update an application type privilege.")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = NexusSecurityApiConstants.PRIVILEGE_MISCONFIGURED),
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS),
      @ApiResponse(code = 404, message = NexusSecurityApiConstants.PRIVILEGE_NOT_FOUND)
  })
  void updatePrivilege(@ApiParam("The id of the privilege to update.") @NotNull final String id,
                       @ApiParam(
                           "The privilege to update.") @NotNull @Valid final ApiPrivilegeApplicationRequest privilege);

  @ApiOperation("Create a wildcard type privilege.")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = NexusSecurityApiConstants.PRIVILEGE_MISCONFIGURED),
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS)
  })
  Response createPrivilege(@ApiParam(
      "The privilege to create.") @NotNull @Valid final ApiPrivilegeWildcardRequest privilege);

  @ApiOperation("Update a wildcard type privilege.")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = NexusSecurityApiConstants.PRIVILEGE_MISCONFIGURED),
      @ApiResponse(code = 403, message = NexusSecurityApiConstants.INVALID_PERMISSIONS),
      @ApiResponse(code = 404, message = NexusSecurityApiConstants.PRIVILEGE_NOT_FOUND)
  })
  void updatePrivilege(@ApiParam("The id of the privilege to update.") @NotNull final String id,
                       @ApiParam(
                           "The privilege to update.") @NotNull @Valid final ApiPrivilegeWildcardRequest privilege);
}
