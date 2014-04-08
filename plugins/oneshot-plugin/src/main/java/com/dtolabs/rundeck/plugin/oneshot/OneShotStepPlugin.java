/*
 * Copyright 2012 DTO Labs, Inc. (http://dtolabs.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/*
* ExampleStepPlugin.java
* 
* User: Greg Schueler <a href="mailto:greg@dtosolutions.com">greg@dtosolutions.com</a>
* Created: 11/9/12 4:09 PM
* 
*/
package com.dtolabs.rundeck.plugin.example;

import com.dtolabs.rundeck.core.execution.workflow.steps.FailureReason;
import com.dtolabs.rundeck.core.execution.workflow.steps.StepException;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.core.plugins.configuration.Describable;
import com.dtolabs.rundeck.core.plugins.configuration.Description;
import com.dtolabs.rundeck.plugins.ServiceNameConstants;
import com.dtolabs.rundeck.plugins.step.PluginStepContext;
import com.dtolabs.rundeck.plugins.step.StepPlugin;
import com.dtolabs.rundeck.plugins.util.DescriptionBuilder;
import com.dtolabs.rundeck.plugins.util.PropertyBuilder;

import java.util.Map;


/**
 */
@Plugin(name = OneShotStepPlugin.SERVICE_PROVIDER_NAME, service = ServiceNameConstants.WorkflowStep)
public class OneShotStepPlugin implements StepPlugin, Describable {

    public static final String SERVICE_PROVIDER_NAME = "com.dtolabs.rundeck.plugin.example.OneShotStepPlugin";


    /**
     */
    public Description getDescription() {
        return DescriptionBuilder.builder()
                .name(SERVICE_PROVIDER_NAME)
                .title("OneShot Step")
                .description("Money shot")
                .property(PropertyBuilder.builder()
                        .select("executionLimit")
                        .title("Execution Limit")
                        .description("Max number of times to execute this task")
                        .required(false)
                        .values("0", "1")
                        .build()
                )
                .build();
    }

    static enum Reason implements FailureReason{
        InsufficientNodes
    }

    /**
     */
    public void executeStep(final PluginStepContext context, final Map<String, Object> configuration) throws
            StepException{
        System.out.println("OneShot step executing on nodes: " + context.getNodes().getNodeNames());
        System.out.println("OneShot step configuration: " + configuration);
        System.out.println("OneShot step num: " + context.getStepNumber());
        System.out.println("OneShot step context: " + context.getStepContext());
//        if (Integer.parseInt(configuration.get("executionLimit").toString()) > context.getNodes().size()) {
//            throw new StepException("executionLimit was > than node count", Reason.InsufficientNodes);
//        }
    }
}
