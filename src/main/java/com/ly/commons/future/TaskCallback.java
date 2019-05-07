package com.ly.commons.future;

/**
 * @author: BG320587
 * @date: 2019/5/7 13:46
 */
public abstract class TaskCallback {

    /**
     * 执行动作
     */
    public abstract TaskFuture apply(TaskFuture f);

    public TaskCallback compose(final TaskCallback before) {
        return new TaskCallback() {
            @Override
            public TaskFuture apply(TaskFuture f) {
                return TaskCallback.this.apply(before.apply(f));
            }
        };
    }

    public TaskCallback andThen(final TaskCallback after) {
        return new TaskCallback() {
            @Override
            public TaskFuture apply(TaskFuture f) {
                return after.apply(TaskCallback.this.apply(f));
            }
        };
    }
}
