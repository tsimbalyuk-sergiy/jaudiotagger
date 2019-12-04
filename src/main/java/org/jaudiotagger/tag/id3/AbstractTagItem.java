/**
 *  @author : Paul Taylor
 *  @author : Eric Farng
 *
 *  Version @version:$Id$
 *
 *  MusicTag Copyright (C)2003,2004
 *
 *  This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 *  General Public  License as published by the Free Software Foundation; either version 2.1 of the License,
 *  or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 *  you can get a copy from http://www.opensource.org/licenses/lgpl-license.php or write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 *  Description:
 *   This class is a facade for all classes that can write to an MP3 file. This includes
 *   fragments and fragment body . It has abstract methods that needs to be implemented,
 *   and a few default implementations of other methods.
 */

package org.jaudiotagger.tag.id3;

import org.jaudiotagger.tag.TagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;


/**
 * This specifies a series of methods that have to be implemented by all structural subclasses,
 * required to support all copy constructors,iterative methods and so on.
 *
 * TODO Not sure if this is really correct, if really needed should probably be an interface
 */
public abstract class AbstractTagItem
{

    //Logger
    public static Logger logger = LoggerFactory.getLogger("org.jaudiotagger.tag.id3");


    public AbstractTagItem()
    {
    }

    public AbstractTagItem(AbstractTagItem copyObject)
    {
        // no copy constructor in super class
    }

    /**
     * ID string that usually corresponds to the class name, but can be
     * displayed to the user. It is not indended to identify each individual
     * instance.
     *
     * @return ID string
     */
    abstract public String getIdentifier();

    /**
     * Return size of this item
     *
     * @return size of this item
     */
    abstract public int getSize();

    /**
     * @param byteBuffer file to read from
     * @throws TagException on any exception generated by this library.
     */
    abstract public void read(ByteBuffer byteBuffer) throws TagException;

    /**
     * Returns true if this datatype is a subset of the argument. This instance
     * is a subset if it is the same class as the argument.
     *
     * @param obj datatype to determine subset of
     * @return true if this instance and its entire datatype array list is a
     *         subset of the argument.
     */
    public boolean isSubsetOf(Object obj)
    {
        return obj instanceof AbstractTagItem;
    }

    /**
     * Returns true if this datatype and its body equals the argument and its
     * body. this datatype is equal if and only if they are the same class
     *
     * @param obj datatype to determine equality of
     * @return true if this datatype and its body are equal
     */
    public boolean equals(Object obj)
    {
        if ( this == obj ) return true;
        return obj instanceof AbstractTagItem;
    }
}
