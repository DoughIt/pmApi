package com.pm.pmapi.common.constant;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 帖子分类
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-06 09:35
 */
public enum TopicFilter {
    ALL {
        @Override
        public Integer getValue() {
            return 0;
        }
    },
    LESSON {
        @Override
        public Integer getValue() {
            return 1;
        }
    },
    GOODS {
        @Override
        public Integer getValue() {
            return 2;
        }
    };
    public abstract Integer getValue();
}
