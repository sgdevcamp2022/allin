package com.All_IN.manager.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.format.annotation.DateTimeFormat;

@UtilityClass
public class SqlDateTime {

    @Embeddable
    @Getter
    public static class All {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "create_at")
        private LocalDateTime createAt;

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "delete_at")
        private LocalDateTime deleteAt;

        public All() {
            this.createAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
            this.deleteAt = null;
        }

        public All modify() {
            this.modifiedAt = LocalDateTime.now();
            return this;
        }

        public All delete() {
            this.deleteAt = LocalDateTime.now();
            return this;
        }
    }

    @Embeddable
    public static class createAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "create_at")
        private LocalDateTime createAt;


        public createAt() {
            this.createAt = LocalDateTime.now();
        }
    }

    @Embeddable
    public static class modifiedAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;


        public modifiedAt() {
            this.modifiedAt = LocalDateTime.now();
        }

        public modifiedAt modify() {
            this.modifiedAt = LocalDateTime.now();
            return this;
        }
    }

    @Embeddable
    public static class deleteAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "delete_at")
        private LocalDateTime deleteAt;


        public deleteAt() {
            this.deleteAt = LocalDateTime.now();
        }

        public deleteAt delete() {
            this.deleteAt = LocalDateTime.now();
            return this;
        }
    }

    @Embeddable
    public static class createAndModifiedAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "create_at")
        private LocalDateTime createAt;

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;


        public createAndModifiedAt() {
            this.createAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
        }

        public createAndModifiedAt modify() {
            this.modifiedAt = LocalDateTime.now();
            return this;
        }
    }

    @Embeddable
    public static class createAndDeleteAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "create_at")
        private LocalDateTime createAt;

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "delete_at")
        private LocalDateTime deleteAt;


        public createAndDeleteAt() {
            this.createAt = LocalDateTime.now();
            this.deleteAt = null;
        }

        public createAndDeleteAt delete() {
            this.deleteAt = LocalDateTime.now();
            return this;
        }
    }

    @Embeddable
    public static class modifiedAndDeleteAt {

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;

        @DateTimeFormat(pattern = "yyy-MM-dd 'T' HH::mm")
        @Column(name = "delete_at")
        private LocalDateTime deleteAt;


        public modifiedAndDeleteAt() {
            this.modifiedAt = null;
            this.deleteAt = null;
        }

        public modifiedAndDeleteAt modify() {
            this.modifiedAt = LocalDateTime.now();
            return this;
        }

        public modifiedAndDeleteAt delete() {
            this.deleteAt = LocalDateTime.now();
            return this;
        }
    }

}
