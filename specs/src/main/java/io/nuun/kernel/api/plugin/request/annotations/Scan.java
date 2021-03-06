/**
 * Copyright (C) 2014 Kametic <epo.jemba@kametic.com>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * or any later version
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nuun.kernel.api.plugin.request.annotations;

import io.nuun.kernel.api.plugin.request.RequestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import org.kametic.specifications.Specification;
import org.kametic.specifications.TrueSpecification;

/**
 * 
 * @author epo.jemba{@literal @}kametic.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Qualifier
public @interface Scan
{
    RequestType type() default RequestType.VIA_SPECIFICATION;
    Class<? extends Specification<?>> value () default TrueSpecification.class;
    String valueString () default "";
    Class<?> valueClass () default Void.class;
    String name() default "";
}
