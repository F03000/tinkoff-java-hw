package seminar.homework.optional;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Task1 {


    public List<Email> create() {
        Email noAttachment = new Email("First!", "No attachment", null);
        Attachment attachment = new Attachment("/mnt/files/image.png", 370);
        Email withAttachment = new Email("Second!", "With attachment", attachment);
        return Arrays.asList(noAttachment, withAttachment);
    }

    class Email implements Serializable {
        private final String subject;
        private final String body;
        private final Attachment attachment;

        Email(String subject, String body, Attachment attachment) {
            this.subject = subject;
            this.body = body;
            this.attachment = attachment;
        }

        String getSubject() {
            return subject;
        }

        String getBody() {
            return body;
        }

        Optional<Attachment> getAttachment() {
            return Optional.ofNullable(attachment);
        }
    }


    /*
    Should be either Serializable or transient,
    otherwise java.io.NotSerializableException
     */
    class Attachment implements Serializable {
        private final String path;
        private final int size;

        Attachment(String path, int size) {
            this.path = path;
            this.size = size;
        }

        String getPath() {
            return path;
        }

        int getSize() {
            return size;
        }
    }
}
