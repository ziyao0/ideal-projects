/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
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
 */
package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;

/**
 * 填充接口
 * <p>
 * 2020/11/30.
 */
public interface IFill {

    @NonNull
    String getName();

    @NonNull
    FieldFill getFieldFill();

}
