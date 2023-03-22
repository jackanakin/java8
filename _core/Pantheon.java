package _core;

import java.util.List;

public class Pantheon {
    private Culture culture;
    private List<Deities> deities;

    public enum Culture {
        NORSE("Norse"), GREEK("Greek"), EGYPTIAN("Egyptian");

        private String name;

        Culture(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Pantheon(Culture culture, List<Deities> deities) {
        this.culture = culture;
        this.deities = deities;
    }

    public void addDeities(Deities deitie) {
        deities.add(deitie);
    }

    public List<Deities> getDeities() {
        return deities;
    }

    public String getCultureName() {
        return culture.getName();
    }

    public Culture getCulture() {
        return culture;
    }
}
