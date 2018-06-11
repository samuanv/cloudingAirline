package com.clouding.airline;

import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.VueloRepository;
import com.clouding.airline.services.VueloService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VueloServiceTest {
    private VueloService service;
    protected VueloRepository repo;
    private List<Vuelo> vuelos;

    public VueloServiceTest(VueloRepository repo) {
        this.repo = repo;
    }

    @Before
    public void setUp() throws Exception {
        service = new VueloService();
        vuelos = new ArrayList<Vuelo>();
        // stub up a list of products
        Vuelo vuelo = new Vuelo();
        vuelo.setNombre("Vuelo1");
        vuelo.setTarifa(22);
        vuelos.add(vuelo);

        Vuelo vuelo2 = new Vuelo();
        vuelo2.setNombre("Vuelo2");
        vuelo2.setTarifa(23);
        vuelos.add(vuelo2);

        repo.saveAll(vuelos);
    }

    /*@Test
    public void testGetProductsWithNoProducts() {
        productManager = new SimpleProductManager();
        assertNull(productManager.getProducts());
    }*/

    @Test
    public void testGetVuelos() {
        List<Vuelo> vueloss = service.findAll();
        assertNotNull(vueloss);
        assertEquals(2, service.findAll().size());

        Vuelo vuelo = vueloss.get(0);
        assertEquals("Vuelo1", vuelo.getNombre());
        assertEquals(22, vuelo.getTarifa());
        vuelo = vueloss.get(1);
        assertEquals("Vuelo12", vuelo.getNombre());
        assertEquals(23, vuelo.getTarifa());
    }

}
