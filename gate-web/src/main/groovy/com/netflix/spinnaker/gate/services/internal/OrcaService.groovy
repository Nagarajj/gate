/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.gate.services.internal

import retrofit.http.*

interface OrcaService {

  @Headers("Content-type: application/context+json")
  @POST("/ops")
  Map doOperation(@Body Map<String, ? extends Object> body)

  @Headers("Accept: application/json")
  @GET("/applications/{application}/tasks")
  List getTasks(@Path("application") String app, @Query("limit") Integer limit, @Query("statuses") String statuses)

  @Headers("Accept: application/json")
  @GET("/v2/applications/{application}/pipelines")
  List getPipelines(@Path("application") String app, @Query("limit") Integer limit, @Query("statuses") String statuses)

  @Headers("Accept: application/json")
  @GET("/projects/{projectId}/pipelines")
  List<Map> getPipelinesForProject(@Path("projectId") String projectId, @Query("limit") Integer limit, @Query("statuses") String statuses)

  @Headers("Accept: application/json")
  @GET("/tasks/{id}")
  Map getTask(@Path("id") String id)

  @Headers("Accept: application/json")
  @DELETE("/tasks/{id}")
  Map deleteTask(@Path("id") String id)

  @Headers("Accept: application/json")
  @GET("/tasks")
  Map all()

  @Headers("Accept: application/json")
  @PUT("/tasks/{id}/cancel")
  Map cancelTask(@Path("id") String id, @Body String ignored)

  @Headers("Accept: application/json")
  @PUT("/tasks/cancel")
  Map cancelTasks(@Body List<String> taskIds)

  @Headers("Accept: application/json")
  @GET("/executions/activeByInstance")
  Map getActiveExecutionsByInstance()

  @Headers("Accept: application/json")
  @GET("/pipelines")
  List getLatestExecutionsByConfigIds(@Query("pipelineConfigIds") String pipelineConfigIds, @Query("limit") Integer limit, @Query("statuses") String statuses)

  @Headers("Accept: application/json")
  @GET("/pipelines/{id}")
  Map getPipeline(@Path("id") String id)

  @Headers("Accept: application/json")
  @GET("/pipelines/{id}/logs")
  List<Map> getPipelineLogs(@Path("id") String id)

  @Headers("Accept: application/json")
  @PUT("/pipelines/{id}/cancel")
  Map cancelPipeline(@Path("id") String id, @Query("reason") reason, @Query("force") force, @Body String ignored)

  @Headers("Accept: application/json")
  @PUT("/pipelines/{id}/pause")
  Map pausePipeline(@Path("id") String id, @Body String ignored)

  @Headers("Accept: application/json")
  @PUT("/pipelines/{id}/resume")
  Map resumePipeline(@Path("id") String id, @Body String ignored)

  @Headers("Accept: application/json")
  @DELETE("/pipelines/{id}")
  Map deletePipeline(@Path("id") String id)

  @Headers("Accept: application/json")
  @PUT("/pipelines/{executionId}/stages/{stageId}/restart")
  Map restartPipelineStage(@Path("executionId") String executionId, @Path("stageId") String stageId, @Body Map restartDetails)

  @Headers("Accept: application/json")
  @POST("/orchestrate")
  Map startPipeline(@Body Map pipelineConfig, @Query("user") String user)

  @Headers("Accept: application/json")
  @PATCH("/pipelines/{executionId}/stages/{stageId}")
  Map updatePipelineStage(@Path("executionId") String executionId, @Path("stageId") String stageId, @Body Map context)

  @Headers("Accept: application/json")
  @GET("/pipelines/{id}/evaluateExpression")
  Map evaluateExpressionForExecution(@Path("id") String executionId, @Query("expression") String pipelineExpression)

  @Headers("Accept: application/json")
  @GET("/webhooks/preconfigured")
  List preconfiguredWebhooks()

  @Headers("Accept: application/json")
  @GET("/pipelineTemplate")
  Map resolvePipelineTemplate(@Query("source") String source)
}
