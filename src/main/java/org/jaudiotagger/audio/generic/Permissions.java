package org.jaudiotagger.audio.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * Outputs permissions to try and identify why we dont have permissions to read/write file
 */
public class Permissions
{
    public static Logger logger = LoggerFactory.getLogger("org.jaudiotagger.audio.generic");

    /**
     * Display Permissions
     *
     * @param path
     * @return
     */
    public static String displayPermissions(Path path)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("File "+path + " permissions\n");
        try
        {
            {
                AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
                if (view != null)
                {
                    sb.append("owner:"+view.getOwner().getName()+"\n");
                    for (AclEntry acl : view.getAcl())
                    {
                        sb.append(acl+"\n");
                    }
                }
            }

            {
                PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class);
                if (view != null)
                {
                    PosixFileAttributes pfa = view.readAttributes();
                    sb.append(":owner:"+pfa.owner().getName()+":group:"+pfa.group().getName()+":"+PosixFilePermissions.toString(pfa.permissions())+"\n");
                }
            }
        }
        catch(IOException ioe)
        {
            logger.error("Unable to read permissions for:"+path.toString());
        }
        return sb.toString();
    }
}
