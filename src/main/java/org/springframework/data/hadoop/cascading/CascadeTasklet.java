/*
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.hadoop.cascading;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import cascading.cascade.Cascade;
import cascading.management.UnitOfWork;
import cascading.stats.CascadingStats;

/**
 * Batch tasklet for executing a {@link Cascade} as part of a job.
 * 
 * @author Costin Leau
 */
public class CascadeTasklet implements Tasklet {

	private UnitOfWork<CascadingStats> unitOfWork;
	private boolean waitToComplete = true;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Runner.run(unitOfWork, waitToComplete);
		return RepeatStatus.FINISHED;
	}

	/**
	 * Sets the unit of work or a {@link Cascade}.
	 *
	 * @param cascade the new cascade
	 */
	public void setUnitOfWork(Cascade cascade) {
		this.unitOfWork = unitOfWork;
	}
}