package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.ChampionTag;
import com.solodive.championselect.repository.ChampionTagRepository;
import com.solodive.championselect.service.ChampionTagService;
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

import com.solodive.championselect.domain.enumeration.ChampionTagValue;
/**
 * Test class for the ChampionTagResource REST controller.
 *
 * @see ChampionTagResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class ChampionTagResourceIntTest {

    private static final ChampionTagValue DEFAULT_TAG = ChampionTagValue.FIGHTER;
    private static final ChampionTagValue UPDATED_TAG = ChampionTagValue.TANK;

    @Autowired
    private ChampionTagRepository championTagRepository;
    
    @Autowired
    private ChampionTagService championTagService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChampionTagMockMvc;

    private ChampionTag championTag;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChampionTagResource championTagResource = new ChampionTagResource(championTagService);
        this.restChampionTagMockMvc = MockMvcBuilders.standaloneSetup(championTagResource)
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
    public static ChampionTag createEntity(EntityManager em) {
        ChampionTag championTag = new ChampionTag()
            .tag(DEFAULT_TAG);
        return championTag;
    }

    @Before
    public void initTest() {
        championTag = createEntity(em);
    }

    @Test
    @Transactional
    public void createChampionTag() throws Exception {
        int databaseSizeBeforeCreate = championTagRepository.findAll().size();

        // Create the ChampionTag
        restChampionTagMockMvc.perform(post("/api/champion-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(championTag)))
            .andExpect(status().isCreated());

        // Validate the ChampionTag in the database
        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeCreate + 1);
        ChampionTag testChampionTag = championTagList.get(championTagList.size() - 1);
        assertThat(testChampionTag.getTag()).isEqualTo(DEFAULT_TAG);
    }

    @Test
    @Transactional
    public void createChampionTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = championTagRepository.findAll().size();

        // Create the ChampionTag with an existing ID
        championTag.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChampionTagMockMvc.perform(post("/api/champion-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(championTag)))
            .andExpect(status().isBadRequest());

        // Validate the ChampionTag in the database
        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTagIsRequired() throws Exception {
        int databaseSizeBeforeTest = championTagRepository.findAll().size();
        // set the field null
        championTag.setTag(null);

        // Create the ChampionTag, which fails.

        restChampionTagMockMvc.perform(post("/api/champion-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(championTag)))
            .andExpect(status().isBadRequest());

        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChampionTags() throws Exception {
        // Initialize the database
        championTagRepository.saveAndFlush(championTag);

        // Get all the championTagList
        restChampionTagMockMvc.perform(get("/api/champion-tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(championTag.getId().intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.toString())));
    }
    
    @Test
    @Transactional
    public void getChampionTag() throws Exception {
        // Initialize the database
        championTagRepository.saveAndFlush(championTag);

        // Get the championTag
        restChampionTagMockMvc.perform(get("/api/champion-tags/{id}", championTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(championTag.getId().intValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChampionTag() throws Exception {
        // Get the championTag
        restChampionTagMockMvc.perform(get("/api/champion-tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChampionTag() throws Exception {
        // Initialize the database
        championTagService.save(championTag);

        int databaseSizeBeforeUpdate = championTagRepository.findAll().size();

        // Update the championTag
        ChampionTag updatedChampionTag = championTagRepository.findById(championTag.getId()).get();
        // Disconnect from session so that the updates on updatedChampionTag are not directly saved in db
        em.detach(updatedChampionTag);
        updatedChampionTag
            .tag(UPDATED_TAG);

        restChampionTagMockMvc.perform(put("/api/champion-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChampionTag)))
            .andExpect(status().isOk());

        // Validate the ChampionTag in the database
        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeUpdate);
        ChampionTag testChampionTag = championTagList.get(championTagList.size() - 1);
        assertThat(testChampionTag.getTag()).isEqualTo(UPDATED_TAG);
    }

    @Test
    @Transactional
    public void updateNonExistingChampionTag() throws Exception {
        int databaseSizeBeforeUpdate = championTagRepository.findAll().size();

        // Create the ChampionTag

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChampionTagMockMvc.perform(put("/api/champion-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(championTag)))
            .andExpect(status().isBadRequest());

        // Validate the ChampionTag in the database
        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChampionTag() throws Exception {
        // Initialize the database
        championTagService.save(championTag);

        int databaseSizeBeforeDelete = championTagRepository.findAll().size();

        // Get the championTag
        restChampionTagMockMvc.perform(delete("/api/champion-tags/{id}", championTag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ChampionTag> championTagList = championTagRepository.findAll();
        assertThat(championTagList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChampionTag.class);
        ChampionTag championTag1 = new ChampionTag();
        championTag1.setId(1L);
        ChampionTag championTag2 = new ChampionTag();
        championTag2.setId(championTag1.getId());
        assertThat(championTag1).isEqualTo(championTag2);
        championTag2.setId(2L);
        assertThat(championTag1).isNotEqualTo(championTag2);
        championTag1.setId(null);
        assertThat(championTag1).isNotEqualTo(championTag2);
    }
}
