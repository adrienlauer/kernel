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
package io.nuun.kernel.core.internal;

import io.nuun.kernel.core.internal.concerns.ConcernTest;
import io.nuun.kernel.core.internal.concerns.sample.BugPlugin;
import io.nuun.kernel.core.internal.concerns.sample.CachePlugin;
import io.nuun.kernel.core.internal.concerns.sample.LogPlugin;
import io.nuun.kernel.core.internal.concerns.sample.SecurityPlugin;
import io.nuun.kernel.core.internal.context.InitContextInternal;

import java.util.HashMap;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.spi.DefaultElementVisitor;
import com.google.inject.spi.StaticInjectionRequest;

public class InternalKernelModuleTest
{

    Injector injector;
	private KernelGuiceModuleInternal underTest;

    @Before
    public void init()
    {

        underTest = new KernelGuiceModuleInternal(new InitContextInternal("nuun-", new HashMap<String, String>()) );
//        Module aggregationModule = new AbstractModule()
//        {
//
//            @Override
//            protected void configure()
//            {
//                bind(Holder.class);
//                bind(HolderForPlugin.class);
//                bind(HolderForContext.class);
//                bind(HolderForPrefixWithName.class);
//                bind(HolderForBeanWithParentType.class);
//                install(underTest);
//            }
//        };
//
//        injector = Guice.createInjector(Stage.PRODUCTION, aggregationModule);
    }

    // @Test
    // public void logger_should_be_injected_even_on_child_class()
    // {
    // Holder holder = injector.getInstance(HolderChild.class);
    // assertThat(holder.getLogger()).isNotNull();
    // }
    //

    
    @Test
    public void computeOrder_should_works ()
    {
    	Assertions.assertThat( underTest.computeOrder(SecurityPlugin.Module.class)).isEqualTo(12884901888L);
    	Assertions.assertThat( underTest.computeOrder(LogPlugin.Module.class)).isEqualTo(-4294967296L);
    	Assertions.assertThat( underTest.computeOrder(CachePlugin.Module.class)).isEqualTo(12884901886L);
    	Assertions.assertThat( underTest.computeOrder(ConcernTest.Module.class)).isEqualTo(0);
    	Assertions.assertThat( underTest.computeOrder(BugPlugin.Module.class)).isEqualTo(15032385535L);
    }
    
    @Test
    @Ignore
    public void injectorCheck()
    {
        for (Key<?> key : injector.getAllBindings().keySet())
        {
            System.err.println("> " + key + " => " + key.getTypeLiteral().getRawType());
            Binding<?> binding = injector.getBinding(key);
            binding.acceptVisitor(new DefaultElementVisitor<Void>()
            {

                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * com.google.inject.spi.DefaultElementVisitor#visit(com.google
                 * .inject.Binding)
                 */
                @Override
                public <T> Void visit(Binding<T> binding)
                {
                    System.err.println(">> Binding " + binding.toString().replaceAll(",", ",\n   ") + " at \n   " + binding.getSource());
                    return null;

                }

                @Override
                public Void visit(StaticInjectionRequest element)
                {
                    System.err.println(">> Static injection is fragile! Please fix " + element.getType().getName() + " at " + element.getSource());
                    return null;
                }
            });

        }
    }

}
