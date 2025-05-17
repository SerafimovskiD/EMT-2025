package mk.ukim.finki.lab.dto;

public class HostCountryStatsDto {

    private String country;
    private Long hostCount;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getHostCount() {
        return hostCount;
    }

    public void setHostCount(Long hostCount) {
        this.hostCount = hostCount;
    }
}
