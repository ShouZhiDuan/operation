package com.nvxclouds.operation.biz.domain.pgsql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/14 15:52
 * @Description: 数据集模型
 */

@Data
@Table(name = "datasets")
public class DataSets {

    @Id
    @Column(name = "id" )
    private Long     id;

    private Timestamp created;

    private Timestamp updated;

    @Column(name = "attributes_name")
    private String attributesName;

    @Column(name = "authorized_institutes")
    private String authorizedInstitutes;

    @Column(name = "authorized_users")
    private String authorizedUsers;

    @Column(name = "block_size")
    private Integer blockSize;

    @Column(name = "database_key")
    private String databaseKey;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private Integer endDate;

    @Column(name = "filename")
    private String filename;

    @Column(name = "image_dimension")
    private String imageDimension;

    @Column(name = "image_set_para")
    private String imageSetPara;

    @Column(name = "is_categorical")
    private String isCategorical;

    @Column(name = "last_upload_date")
    private Timestamp lastUploadDate;

    @Column(name = "linkage_filename")
    private String linkageFilename;

    @Column(name = "localdbid")
    private Integer localdbid;

    @Column(name = "max_value")
    private String maxValue;

    @Column(name = "methods")
    private String methods;

    @Column(name = "min_value")
    private String minValue;

    @Column(name = "model_list")
    private String modelList;

    @Column(name = "name")
    private String name;

    @Column(name = "num_categorical")
    private String numCategorical;

    @Column(name = "num_of_files")
    private Integer numOfFiles;

    @Column(name = "number_of_lines")
    private Long numberOfLines;

    @Column(name = "sample_size")
    private Long sampleSize;

    @Column(name = "sample_size_evaluation")
    private Long sampleSizeEvaluation;

    @Column(name = "sample_size_training")
    private Long sampleSizeTraining;

    @Column(name = "size_of_linkage_file")
    private Float sizeOfLinkageFile;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "status")
    private String status;

    @Column(name = "synced")
    private Boolean synced;

    @Column(name = "value_categorical")
    private String valueCategorical;

    @Column(name = "datanode_id")
    private Long datanodeId;

}
