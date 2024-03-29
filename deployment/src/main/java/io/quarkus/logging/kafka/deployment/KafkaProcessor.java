/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
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
package io.quarkus.logging.kafka.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.LogHandlerBuildItem;
import io.quarkus.logging.kafka.KafkaConfig;
import io.quarkus.logging.kafka.KafkaHandlerValueFactory;

class KafkaProcessor {

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem("log-kafka");
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    LogHandlerBuildItem addKafkaLogHandler(final KafkaConfig kafkaConfig, final KafkaHandlerValueFactory kafkaHandlerValueFactory) {
        return new LogHandlerBuildItem(kafkaHandlerValueFactory.create(kafkaConfig));
    }
}