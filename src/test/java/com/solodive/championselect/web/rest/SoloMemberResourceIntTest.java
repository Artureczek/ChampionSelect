package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.SoloMember;
import com.solodive.championselect.repository.SoloMemberRepository;
import com.solodive.championselect.service.SoloMemberService;
import com.solodive.championselect.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.solodive.championselect.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SoloMemberResource REST controller.
 *
 * @see SoloMemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class SoloMemberResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_HOMETOWN = "AAAAAAAAAA";
    private static final String UPDATED_HOMETOWN = "BBBBBBBBBB";

    @Autowired
    private SoloMemberRepository soloMemberRepository;
    
    @Autowired
    private SoloMemberService soloMemberService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSoloMemberMockMvc;

    private SoloMember soloMember;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SoloMemberResource soloMemberResource = new SoloMemberResource(soloMemberService);
        this.restSoloMemberMockMvc = MockMvcBuilders.standaloneSetup(soloMemberResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoloMember createEntity(EntityManager em) {
        SoloMember soloMember = new SoloMember()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME)
            .description(DEFAULT_DESCRIPTION)
            .hometown(DEFAULT_HOMETOWN);
        return soloMember;
    }

    @Before
    public void initTest() {
        soloMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createSoloMember() throws Exception {
        int databaseSizeBeforeCreate = soloMemberRepository.findAll().size();

        // Create the SoloMember
        restSoloMemberMockMvc.perform(post("/api/solo-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soloMember)))
            .andExpect(status().isCreated());

        // Validate the SoloMember in the database
        List<SoloMember> soloMemberList = soloMemberRepository.findAll();
        assertThat(soloMemberList).hasSize(databaseSizeBeforeCreate + 1);
        SoloMember testSoloMember = soloMemberList.get(soloMemberList.size() - 1);
        assertThat(testSoloMember.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSoloMember.getSurname()).isEqualTo(DEFAULT_SURNAME);
        assertThat(testSoloMember.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSoloMember.getHometown()).isEqualTo(DEFAULT_HOMETOWN);
    }

    @Test
    @Transactional
    public void createSoloMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = soloMemberRepository.findAll().size();

        // Create the SoloMember with an existing ID
        soloMember.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoloMemberMockMvc.perform(post("/api/solo-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soloMember)))
            .andExpect(status().isBadRequest());

        // Validate the SoloMember in the database
        List<SoloMember> soloMemberList = soloMemberRepository.findAll();
        assertThat(soloMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSoloMembers() throws Exception {
        // Initialize the database
        soloMemberRepository.saveAndFlush(soloMember);

        // Get all the soloMemberList
        restSoloMemberMockMvc.perform(get("/api/solo-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soloMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].hometown").value(hasItem(DEFAULT_HOMETOWN.toString())));
    }
    
    @Test
    @Transactional
    public void getSoloMember() throws Exception {
        // Initialize the database
        soloMemberRepository.saveAndFlush(soloMember);

        // Get the soloMember
        restSoloMemberMockMvc.perform(get("/api/solo-members/{id}", soloMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(soloMember.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.hometown").value(DEFAULT_HOMETOWN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSoloMember() throws Exception {
        // Get the soloMember
        restSoloMemberMockMvc.perform(get("/api/solo-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSoloMember() throws Exception {
        // Initialize the database
        soloMemberService.save(soloMember);

        int databaseSizeBeforeUpdate = soloMemberRepository.findAll().size();

        // Update the soloMember
        SoloMember updatedSoloMember = soloMemberRepository.findById(soloMember.getId()).get();
        // Disconnect from session so that the updates on updatedSoloMember are not directly saved in db
        em.detach(updatedSoloMember);
        updatedSoloMember
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME)
            .description(UPDATED_DESCRIPTION)
            .hometown(UPDATED_HOMETOWN);

        restSoloMemberMockMvc.perform(put("/api/solo-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSoloMember)))
            .andExpect(status().isOk());

        // Validate the SoloMember in the database
        List<SoloMember> soloMemberList = soloMemberRepository.findAll();
        assertThat(soloMemberList).hasSize(databaseSizeBeforeUpdate);
        SoloMember testSoloMember = soloMemberList.get(soloMemberList.size() - 1);
        assertThat(testSoloMember.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSoloMember.getSurname()).isEqualTo(UPDATED_SURNAME);
        assertThat(testSoloMember.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSoloMember.getHometown()).isEqualTo(UPDATED_HOMETOWN);
    }

    @Test
    @Transactional
    public void updateNonExistingSoloMember() throws Exception {
        int databaseSizeBeforeUpdate = soloMemberRepository.findAll().size();

        // Create the SoloMember

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoloMemberMockMvc.perform(put("/api/solo-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(soloMember)))
            .andExpect(status().isBadRequest());

        // Validate the SoloMember in the database
        List<SoloMember> soloMemberList = soloMemberRepository.findAll();
        assertThat(soloMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSoloMember() throws Exception {
        // Initialize the database
        soloMemberService.save(soloMember);

        int databaseSizeBeforeDelete = soloMemberRepository.findAll().size();

        // Get the soloMember
        restSoloMemberMockMvc.perform(delete("/api/solo-members/{id}", soloMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SoloMember> soloMemberList = soloMemberRepository.findAll();
        assertThat(soloMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoloMember.class);
        SoloMember soloMember1 = new SoloMember();
        soloMember1.setId(1L);
        SoloMember soloMember2 = new SoloMember();
        soloMember2.setId(soloMember1.getId());
        assertThat(soloMember1).isEqualTo(soloMember2);
        soloMember2.setId(2L);
        assertThat(soloMember1).isNotEqualTo(soloMember2);
        soloMember1.setId(null);
        assertThat(soloMember1).isNotEqualTo(soloMember2);
    }
}
