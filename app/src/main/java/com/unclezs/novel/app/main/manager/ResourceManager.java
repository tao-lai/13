package com.unclezs.novel.app.main.manager;

import cn.hutool.core.io.FileUtil;
import com.unclezs.novel.analyzer.util.StringUtils;
import com.unclezs.novel.app.framework.util.ResourceUtils;
import java.io.File;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;

/**
 * 资源管理器
 *
 * @author blog.unclezs.com
 * @date 2021/4/24 0:35
 */
@UtilityClass
public class ResourceManager {

  private static final String WORK_DIR = FileUtil.normalize(new File(".").getAbsolutePath());
  /**
   * 配置文件文件夹
   */
  public static final File CONF_DIR = FileUtil.file(WORK_DIR, "conf");
  /**
   * 插件文件夹
   */
  public static final File PLUGINS_DIR = FileUtil.file(WORK_DIR, "plugins");
  /**
   * 执行文件文件夹
   */
  public static final File BIN_DIR = FileUtil.file(WORK_DIR, "bin");

  /**
   * 获取配置文件目录下的文件
   *
   * @param path 文件路径
   * @return 文件
   */
  public static File confFile(String path) {
    return FileUtil.file(CONF_DIR, path);
  }

  /**
   * 获取配置文件目录下的文件
   *
   * @param path 文件路径
   * @return 文件
   */
  public static String readConfFile(String path) {
    return FileUtil.readUtf8String(FileUtil.file(CONF_DIR, path));
  }

  /**
   * 获取配置文件目录下的文件
   *
   * @param path 文件路径
   */
  public static void saveConfFile(String path, String content) {
    FileUtil.writeUtf8String(content, FileUtil.file(CONF_DIR, path));
  }

  public static File binFile(String path) {
    return FileUtil.file(BIN_DIR, path);
  }

  public static File pluginsFile(String path) {
    return FileUtil.file(PLUGINS_DIR, path);
  }

  /**
   * 查找资源
   * <li>1.绝对路径</li>
   * <li>2.classpath</li>
   * <li>3.相对于app运行目录</li>
   *
   * @param location 资源路径
   * @return 资源位置
   */
  public static String findResource(String location) {
    if (StringUtils.isBlank(location)) {
      return null;
    }
    if (Path.of(location).isAbsolute()) {
      return location;
    }
    if (ResourceUtils.exist(location)) {
      return ResourceUtils.externalForm(location);
    }
    File resource = FileUtil.file(WORK_DIR, location);
    if (resource.exists()) {
      return resource.getAbsolutePath();
    }
    return null;
  }
}
