package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.Image;
import com.solodive.championselect.repository.ImageRepository;
import com.solodive.championselect.service.ImageService;
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
 * Test class for the ImageResource REST controller.
 *
 * @see ImageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class ImageResourceIntTest {

    private static final Long DEFAULT_WIDTH_START = 1L;
    private static final Long UPDATED_WIDTH_START = 2L;

    private static final Long DEFAULT_HEIGHT_START = 1L;
    private static final Long UPDATED_HEIGHT_START = 2L;

    private static final Long DEFAULT_WIDTH_END = 1L;
    private static final Long UPDATED_WIDTH_END = 2L;

    private static final Long DEFAULT_HEIGHT_END = 1L;
    private static final Long UPDATED_HEIGHT_END = 2L;

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_SPRITE = "AAAAAAAAAA";
    private static final String UPDATED_SPRITE = "BBBBBBBBBB";

    private static final String DEFAULT_FULL = "AAAAAAAAAA";
    private static final String UPDATED_FULL = "BBBBBBBBBB";

    @Autowired
    private ImageRepository imageRepository;
    
    @Autowired
    private ImageService imageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restImageMockMvc;

    private Image image;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageResource imageResource = new ImageResource(imageService);
        this.restImageMockMvc = MockMvcBuilders.standaloneSetup(imageResource)
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
    public static Image createEntity(EntityManager em) {
        Image image = new Image()
            .widthStart(DEFAULT_WIDTH_START)
            .heightStart(DEFAULT_HEIGHT_START)
            .widthEnd(DEFAULT_WIDTH_END)
            .heightEnd(DEFAULT_HEIGHT_END)
            .group(DEFAULT_GROUP)
            .sprite(DEFAULT_SPRITE)
            .full(DEFAULT_FULL);
        return image;
    }

    @Before
    public void initTest() {
        image = createEntity(em);
    }

    @Test
    @Transactional
    public void createImage() throws Exception {
        int databaseSizeBeforeCreate = imageRepository.findAll().size();

        // Create the Image
        restImageMockMvc.perform(post("/api/images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(image)))
            .andExpect(status().isCreated());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeCreate + 1);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getWidthStart()).isEqualTo(DEFAULT_WIDTH_START);
        assertThat(testImage.getHeightStart()).isEqualTo(DEFAULT_HEIGHT_START);
        assertThat(testImage.getWidthEnd()).isEqualTo(DEFAULT_WIDTH_END);
        assertThat(testImage.getHeightEnd()).isEqualTo(DEFAULT_HEIGHT_END);
        assertThat(testImage.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testImage.getSprite()).isEqualTo(DEFAULT_SPRITE);
        assertThat(testImage.getFull()).isEqualTo(DEFAULT_FULL);
    }

    @Test
    @Transactional
    public void createImageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageRepository.findAll().size();

        // Create the Image with an existing ID
        image.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageMockMvc.perform(post("/api/images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(image)))
            .andExpect(status().isBadRequest());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImages() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get all the imageList
        restImageMockMvc.perform(get("/api/images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
            .andExpect(jsonPath("$.[*].widthStart").value(hasItem(DEFAULT_WIDTH_START.intValue())))
            .andExpect(jsonPath("$.[*].heightStart").value(hasItem(DEFAULT_HEIGHT_START.intValue())))
            .andExpect(jsonPath("$.[*].widthEnd").value(hasItem(DEFAULT_WIDTH_END.intValue())))
            .andExpect(jsonPath("$.[*].heightEnd").value(hasItem(DEFAULT_HEIGHT_END.intValue())))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP.toString())))
            .andExpect(jsonPath("$.[*].sprite").value(hasItem(DEFAULT_SPRITE.toString())))
            .andExpect(jsonPath("$.[*].full").value(hasItem(DEFAULT_FULL.toString())));
    }
    
    @Test
    @Transactional
    public void getImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get the image
        restImageMockMvc.perform(get("/api/images/{id}", image.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(image.getId().intValue()))
            .andExpect(jsonPath("$.widthStart").value(DEFAULT_WIDTH_START.intValue()))
            .andExpect(jsonPath("$.heightStart").value(DEFAULT_HEIGHT_START.intValue()))
            .andExpect(jsonPath("$.widthEnd").value(DEFAULT_WIDTH_END.intValue()))
            .andExpect(jsonPath("$.heightEnd").value(DEFAULT_HEIGHT_END.intValue()))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP.toString()))
            .andExpect(jsonPath("$.sprite").value(DEFAULT_SPRITE.toString()))
            .andExpect(jsonPath("$.full").value(DEFAULT_FULL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImage() throws Exception {
        // Get the image
        restImageMockMvc.perform(get("/api/images/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImage() throws Exception {
        // Initialize the database
        imageService.save(image);

        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Update the image
        Image updatedImage = imageRepository.findById(image.getId()).get();
        // Disconnect from session so that the updates on updatedImage are not directly saved in db
        em.detach(updatedImage);
        updatedImage
            .widthStart(UPDATED_WIDTH_START)
            .heightStart(UPDATED_HEIGHT_START)
            .widthEnd(UPDATED_WIDTH_END)
            .heightEnd(UPDATED_HEIGHT_END)
            .group(UPDATED_GROUP)
            .sprite(UPDATED_SPRITE)
            .full(UPDATED_FULL);

        restImageMockMvc.perform(put("/api/images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedImage)))
            .andExpect(status().isOk());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getWidthStart()).isEqualTo(UPDATED_WIDTH_START);
        assertThat(testImage.getHeightStart()).isEqualTo(UPDATED_HEIGHT_START);
        assertThat(testImage.getWidthEnd()).isEqualTo(UPDATED_WIDTH_END);
        assertThat(testImage.getHeightEnd()).isEqualTo(UPDATED_HEIGHT_END);
        assertThat(testImage.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testImage.getSprite()).isEqualTo(UPDATED_SPRITE);
        assertThat(testImage.getFull()).isEqualTo(UPDATED_FULL);
    }

    @Test
    @Transactional
    public void updateNonExistingImage() throws Exception {
        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Create the Image

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageMockMvc.perform(put("/api/images")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(image)))
            .andExpect(status().isBadRequest());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImage() throws Exception {
        // Initialize the database
        imageService.save(image);

        int databaseSizeBeforeDelete = imageRepository.findAll().size();

        // Get the image
        restImageMockMvc.perform(delete("/api/images/{id}", image.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Image.class);
        Image image1 = new Image();
        image1.setId(1L);
        Image image2 = new Image();
        image2.setId(image1.getId());
        assertThat(image1).isEqualTo(image2);
        image2.setId(2L);
        assertThat(image1).isNotEqualTo(image2);
        image1.setId(null);
        assertThat(image1).isNotEqualTo(image2);
    }
}
