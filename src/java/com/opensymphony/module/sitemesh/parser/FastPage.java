/*
 * Title:        FastPage
 * Description:
 *
 * This software is published under the terms of the OpenSymphony Software
 * License version 1.1, of which a copy has been included with this
 * distribution in the LICENSE.txt file.
 */

package com.opensymphony.module.sitemesh.parser;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/**
 * HTMLPage implementation produced by FastPageParser.
 *
 * @author <a href="mailto:salaman@qoretech.com">Victor Salaman</a>
 * @version $Revision: 1.1 $
 */
public final class FastPage extends AbstractHTMLPage
{
   private String head;
   private String body;
   private boolean frameSet;

   public FastPage(Map sitemeshProps, Map htmlProps, Map metaProps, Map bodyProps,
                   String title, String head, String body, boolean frameSet)
   {
      this.head = head;
      this.body = body;
      this.frameSet = frameSet;
      addAttributeList(null, htmlProps);
      addAttributeList("page", sitemeshProps);
      addAttributeList("body", bodyProps);
      addAttributeList("meta", metaProps);
      addProperty("title", title);
   }

   public void writeHead(Writer out) throws IOException
   {
      out.write(head);
   }

   public void writeBody(Writer out) throws IOException
   {
      out.write(body);
   }

   public void writePage(Writer out) throws IOException
   {
      out.write(this.pageData);
   }

   private void addAttributeList(String prefix, Map attributes)
   {
      if(attributes == null || attributes.isEmpty()) return;

      if(prefix == null) prefix = "";
      if(prefix.length() > 0) prefix += '.';

      String name, value;
      Iterator i = attributes.keySet().iterator();

      while (i.hasNext())
      {
         name = ( String ) i.next();
         value = ( String ) attributes.get(name);

         if(value != null && value.trim().length() > 0)
         {
            addProperty(prefix + name, value);
         }
      }
   }

   public void setVerbatimPage(char[] v)
   {
      this.pageData = v;
   }

   public boolean isFrameSet()
   {
      return frameSet;
   }

   public String getBody()
   {
      return body;
   }

   public String getHead()
   {
      return head;
   }
}