package com.clouding.airline.dto;

public class AgenciaDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Integer num_reservas;

    /**
     * @return the num_reservas
     */
    public Integer getNum_reservas() {
        return num_reservas;
    }
    /**
     * @param num_reservas the num_reservas to set
     */
    public void setNum_reservas(Integer num_reservas) {
        this.num_reservas = num_reservas;
    }
    public AgenciaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
