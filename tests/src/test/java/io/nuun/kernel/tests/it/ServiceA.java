/**
 * Copyright (C) 2013-2014 Kametic <epo.jemba@kametic.com>
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
package io.nuun.kernel.tests.it;

import io.nuun.kernel.tests.it.annotations.ITBind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejemba
 *
 */
@ITBind
public class ServiceA
{

    
    Logger logger = LoggerFactory.getLogger(ServiceA.class);
    
    
    public void doSomethingUsefull ()
    {
        logger.info("usefull stuff done !");
    }
    
}
