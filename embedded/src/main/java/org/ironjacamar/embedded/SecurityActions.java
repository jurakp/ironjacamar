/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2015, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 1.0 as
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse
 * Public License for more details.
 *
 * You should have received a copy of the Eclipse Public License 
 * along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.ironjacamar.embedded;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Privileged Blocks
 * @author <a href="mailto:jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
class SecurityActions
{ 
   /**
    * Constructor
    */
   private SecurityActions()
   {
   }

   /**
    * Get the classloader.
    * @param c The class
    * @return The classloader
    */
   static ClassLoader getClassLoader(final Class<?> c)
   {
      if (System.getSecurityManager() == null)
         return c.getClassLoader();

      return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>()
      {
         public ClassLoader run()
         {
            return c.getClassLoader();
         }
      });
   }

   /**
    * Get a system property
    * @param name The property name
    * @return The property value
    */
   static String getSystemProperty(final String name)
   {
      return (String)AccessController.doPrivileged(new PrivilegedAction<Object>() 
      {
         public Object run()
         {
            return System.getProperty(name);
         }
      });
   }

   /**
    * Get a system property
    * @param name The property name
    * @param value The default property value
    * @return The property value
    */
   static String getSystemProperty(final String name, final String value)
   {
      return (String)AccessController.doPrivileged(new PrivilegedAction<Object>() 
      {
         public Object run()
         {
            return System.getProperty(name, value);
         }
      });
   }

   /**
    * Set a system property
    * @param name The property name
    * @param value The property value
    */
   static void setSystemProperty(final String name, final String value)
   {
      AccessController.doPrivileged(new PrivilegedAction<Object>() 
      {
         public Object run()
         {
            System.setProperty(name, value);
            return null;
         }
      });
   }
}
