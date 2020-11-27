package com.evavrynchuk.blog.config;

public interface ConfigProperties {

    String APP_VERSION = "git.build.version";
    String SCM_BRANCH = "git.branch";
    String SCM_TAG = "git.tags";
    String SCM_COMMIT = "git.commit.id.abbrev";
    String BUILD_TIMESTAMP = "git.build.time";

    String HEALTH_MEMORY_MAX_PERCENTAGE = "health.memory.max-percentage";
    String HEALTH_CPU_MAX_PERCENTAGE = "health.cpu.max-percentage";

}
