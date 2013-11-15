package org.erlide.engine.model.root;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.Lists;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.erlide.engine.model.root.PathSerializer;
import org.erlide.engine.model.root.ProjectPreferencesConstants;
import org.erlide.runtime.api.RuntimeCore;
import org.erlide.runtime.runtimeinfo.IRuntimeInfoCatalog;
import org.erlide.runtime.runtimeinfo.RuntimeInfo;
import org.erlide.runtime.runtimeinfo.RuntimeVersion;

@SuppressWarnings("all")
public class ErlangProjectProperties {
  private IPath _outputDir;
  
  public IPath getOutputDir() {
    return this._outputDir;
  }
  
  public void setOutputDir(final IPath outputDir) {
    this._outputDir = outputDir;
  }
  
  private Collection<IPath> _sourceDirs;
  
  private Collection<IPath> _includeDirs;
  
  private String _externalIncludesFile;
  
  public String getExternalIncludesFile() {
    return this._externalIncludesFile;
  }
  
  public void setExternalIncludesFile(final String externalIncludesFile) {
    this._externalIncludesFile = externalIncludesFile;
  }
  
  private String _externalModulesFile;
  
  public String getExternalModulesFile() {
    return this._externalModulesFile;
  }
  
  public void setExternalModulesFile(final String externalModulesFile) {
    this._externalModulesFile = externalModulesFile;
  }
  
  private RuntimeVersion _requiredRuntimeVersion;
  
  public RuntimeVersion getRequiredRuntimeVersion() {
    return this._requiredRuntimeVersion;
  }
  
  public void setRequiredRuntimeVersion(final RuntimeVersion requiredRuntimeVersion) {
    this._requiredRuntimeVersion = requiredRuntimeVersion;
  }
  
  private boolean _nukeOutputOnClean;
  
  public boolean isNukeOutputOnClean() {
    return this._nukeOutputOnClean;
  }
  
  public void setNukeOutputOnClean(final boolean nukeOutputOnClean) {
    this._nukeOutputOnClean = nukeOutputOnClean;
  }
  
  private Charset _encoding;
  
  public Charset getEncoding() {
    return this._encoding;
  }
  
  public void setEncoding(final Charset encoding) {
    this._encoding = encoding;
  }
  
  private Object _builderData;
  
  public Object getBuilderData() {
    return this._builderData;
  }
  
  public void setBuilderData(final Object builderData) {
    this._builderData = builderData;
  }
  
  private final ErlangProjectProperties _defaults;
  
  public ErlangProjectProperties getDefaults() {
    return this._defaults;
  }
  
  private final static ErlangProjectProperties defaultProperties = new Function0<ErlangProjectProperties>() {
    public ErlangProjectProperties apply() {
      ErlangProjectProperties _erlangProjectProperties = new ErlangProjectProperties();
      final Procedure1<ErlangProjectProperties> _function = new Procedure1<ErlangProjectProperties>() {
        public void apply(final ErlangProjectProperties it) {
          Collection<IPath> _unpackList = PathSerializer.unpackList(ProjectPreferencesConstants.DEFAULT_SOURCE_DIRS);
          it._sourceDirs = _unpackList;
          Path _path = new Path(ProjectPreferencesConstants.DEFAULT_OUTPUT_DIR);
          it._outputDir = _path;
          Collection<IPath> _unpackList_1 = PathSerializer.unpackList(ProjectPreferencesConstants.DEFAULT_INCLUDE_DIRS);
          it._includeDirs = _unpackList_1;
          it._externalIncludesFile = ProjectPreferencesConstants.DEFAULT_EXTERNAL_INCLUDES;
          it._externalModulesFile = ProjectPreferencesConstants.DEFAULT_EXTERNAL_MODULES;
          RuntimeVersion _runtimeVersion = new RuntimeVersion(ProjectPreferencesConstants.DEFAULT_RUNTIME_VERSION);
          it._requiredRuntimeVersion = _runtimeVersion;
          it._nukeOutputOnClean = false;
          RuntimeVersion _runtimeVersion_1 = new RuntimeVersion(18);
          boolean _isCompatible = it._requiredRuntimeVersion.isCompatible(_runtimeVersion_1);
          if (_isCompatible) {
            it._encoding = Charsets.UTF_8;
          } else {
            it._encoding = Charsets.ISO_8859_1;
          }
        }
      };
      ErlangProjectProperties _doubleArrow = ObjectExtensions.<ErlangProjectProperties>operator_doubleArrow(_erlangProjectProperties, _function);
      return _doubleArrow;
    }
  }.apply();
  
  public ErlangProjectProperties() {
    ArrayList<IPath> _newArrayList = CollectionLiterals.<IPath>newArrayList();
    this._sourceDirs = _newArrayList;
    Path _path = new Path("");
    this._outputDir = _path;
    ArrayList<IPath> _newArrayList_1 = CollectionLiterals.<IPath>newArrayList();
    this._includeDirs = _newArrayList_1;
    this._externalIncludesFile = "";
    this._externalModulesFile = "";
    RuntimeVersion _runtimeVersion = new RuntimeVersion(ProjectPreferencesConstants.DEFAULT_RUNTIME_VERSION);
    this._requiredRuntimeVersion = _runtimeVersion;
    this._nukeOutputOnClean = false;
    RuntimeVersion _runtimeVersion_1 = new RuntimeVersion(18);
    boolean _isCompatible = this._requiredRuntimeVersion.isCompatible(_runtimeVersion_1);
    if (_isCompatible) {
      this._encoding = Charsets.UTF_8;
    } else {
      this._encoding = Charsets.ISO_8859_1;
    }
    this._defaults = ErlangProjectProperties.defaultProperties;
  }
  
  public Collection<IPath> getIncludeDirs() {
    Collection<IPath> _unmodifiableCollection = Collections.<IPath>unmodifiableCollection(this._includeDirs);
    return _unmodifiableCollection;
  }
  
  public void setIncludeDirs(final Collection<IPath> includeDirs2) {
    ArrayList<IPath> _newArrayList = Lists.<IPath>newArrayList(includeDirs2);
    this._includeDirs = _newArrayList;
  }
  
  public void setIncludeDirs(final IPath... includeDirs2) {
    ArrayList<IPath> _newArrayList = Lists.<IPath>newArrayList(includeDirs2);
    this._includeDirs = _newArrayList;
  }
  
  public Collection<IPath> getSourceDirs() {
    Collection<IPath> _unmodifiableCollection = Collections.<IPath>unmodifiableCollection(this._sourceDirs);
    return _unmodifiableCollection;
  }
  
  public void setSourceDirs(final Collection<IPath> sourceDirs2) {
    ArrayList<IPath> _newArrayList = Lists.<IPath>newArrayList(sourceDirs2);
    this._sourceDirs = _newArrayList;
  }
  
  public void setSourceDirs(final IPath... sourceDirs2) {
    ArrayList<IPath> _newArrayList = Lists.<IPath>newArrayList(sourceDirs2);
    this._sourceDirs = _newArrayList;
  }
  
  public RuntimeVersion copyFrom(final ErlangProjectProperties erlangProjectProperties) {
    RuntimeVersion _xblockexpression = null;
    {
      final ErlangProjectProperties bprefs = erlangProjectProperties;
      this._includeDirs = bprefs._includeDirs;
      this._sourceDirs = bprefs._sourceDirs;
      this._outputDir = bprefs._outputDir;
      RuntimeVersion __requiredRuntimeVersion = this._requiredRuntimeVersion = bprefs._requiredRuntimeVersion;
      _xblockexpression = (__requiredRuntimeVersion);
    }
    return _xblockexpression;
  }
  
  public RuntimeInfo getRuntimeInfo() {
    RuntimeInfo _xblockexpression = null;
    {
      IRuntimeInfoCatalog _runtimeInfoCatalog = RuntimeCore.getRuntimeInfoCatalog();
      final RuntimeInfo runtime = _runtimeInfoCatalog.getRuntime(this._requiredRuntimeVersion, null);
      _xblockexpression = (runtime);
    }
    return _xblockexpression;
  }
  
  public RuntimeVersion getRuntimeVersion() {
    RuntimeVersion _xblockexpression = null;
    {
      final RuntimeInfo runtimeInfo = this.getRuntimeInfo();
      RuntimeVersion _xifexpression = null;
      boolean _tripleNotEquals = (runtimeInfo != null);
      if (_tripleNotEquals) {
        RuntimeVersion _version = runtimeInfo.getVersion();
        _xifexpression = _version;
      } else {
        _xifexpression = this._requiredRuntimeVersion;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public Charset setRuntimeVersion(final RuntimeVersion runtimeVersion) {
    Charset _xblockexpression = null;
    {
      this._requiredRuntimeVersion = runtimeVersion;
      Charset _xifexpression = null;
      RuntimeVersion _runtimeVersion = new RuntimeVersion(18);
      boolean _isCompatible = this._requiredRuntimeVersion.isCompatible(_runtimeVersion);
      if (_isCompatible) {
        Charset __encoding = this._encoding = Charsets.UTF_8;
        _xifexpression = __encoding;
      } else {
        Charset __encoding_1 = this._encoding = Charsets.ISO_8859_1;
        _xifexpression = __encoding_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public boolean sameAs(final Object other1) {
    boolean _tripleEquals = (this == other1);
    if (_tripleEquals) {
      return true;
    }
    boolean _tripleEquals_1 = (other1 == null);
    if (_tripleEquals_1) {
      return false;
    }
    boolean _not = (!(other1 instanceof ErlangProjectProperties));
    if (_not) {
      return false;
    }
    final ErlangProjectProperties other = ((ErlangProjectProperties) other1);
    boolean _tripleEquals_2 = (this._outputDir == null);
    if (_tripleEquals_2) {
      boolean _tripleNotEquals = (other._outputDir != null);
      if (_tripleNotEquals) {
        return false;
      }
    } else {
      boolean _equals = this._outputDir.equals(other._outputDir);
      boolean _not_1 = (!_equals);
      if (_not_1) {
        return false;
      }
    }
    boolean _tripleEquals_3 = (this._sourceDirs == null);
    if (_tripleEquals_3) {
      boolean _tripleNotEquals_1 = (other._sourceDirs != null);
      if (_tripleNotEquals_1) {
        return false;
      }
    } else {
      boolean _equals_1 = this._sourceDirs.equals(other._sourceDirs);
      boolean _not_2 = (!_equals_1);
      if (_not_2) {
        return false;
      }
    }
    boolean _tripleEquals_4 = (this._includeDirs == null);
    if (_tripleEquals_4) {
      boolean _tripleNotEquals_2 = (other._includeDirs != null);
      if (_tripleNotEquals_2) {
        return false;
      }
    } else {
      boolean _equals_2 = this._includeDirs.equals(other._includeDirs);
      boolean _not_3 = (!_equals_2);
      if (_not_3) {
        return false;
      }
    }
    boolean _tripleEquals_5 = (this._externalIncludesFile == null);
    if (_tripleEquals_5) {
      boolean _tripleNotEquals_3 = (other._externalIncludesFile != null);
      if (_tripleNotEquals_3) {
        return false;
      }
    } else {
      boolean _equals_3 = this._externalIncludesFile.equals(other._externalIncludesFile);
      boolean _not_4 = (!_equals_3);
      if (_not_4) {
        return false;
      }
    }
    boolean _tripleEquals_6 = (this._externalModulesFile == null);
    if (_tripleEquals_6) {
      boolean _tripleNotEquals_4 = (other._externalModulesFile != null);
      if (_tripleNotEquals_4) {
        return false;
      }
    } else {
      boolean _equals_4 = this._externalModulesFile.equals(other._externalModulesFile);
      boolean _not_5 = (!_equals_4);
      if (_not_5) {
        return false;
      }
    }
    boolean _tripleEquals_7 = (this._requiredRuntimeVersion == null);
    if (_tripleEquals_7) {
      boolean _tripleNotEquals_5 = (other._requiredRuntimeVersion != null);
      if (_tripleNotEquals_5) {
        return false;
      }
    } else {
      boolean _equals_5 = this._requiredRuntimeVersion.equals(other._requiredRuntimeVersion);
      boolean _not_6 = (!_equals_5);
      if (_not_6) {
        return false;
      }
    }
    boolean _notEquals = (other._nukeOutputOnClean != this._nukeOutputOnClean);
    if (_notEquals) {
      return false;
    }
    boolean _tripleEquals_8 = (this._encoding == null);
    if (_tripleEquals_8) {
      boolean _tripleNotEquals_6 = (other._encoding != null);
      if (_tripleNotEquals_6) {
        return false;
      }
    } else {
      boolean _equals_6 = this._encoding.equals(other._encoding);
      boolean _not_7 = (!_equals_6);
      if (_not_7) {
        return false;
      }
    }
    return true;
  }
  
  public String toString() {
    String _xblockexpression = null;
    {
      ToStringHelper _stringHelper = Objects.toStringHelper(this);
      final Procedure1<ToStringHelper> _function = new Procedure1<ToStringHelper>() {
        public void apply(final ToStringHelper it) {
          it.add("outputDir", ErlangProjectProperties.this._outputDir);
          it.add("sources", ErlangProjectProperties.this._sourceDirs);
          it.add("includes", ErlangProjectProperties.this._includeDirs);
          it.add("runtimeVersion", ErlangProjectProperties.this._requiredRuntimeVersion);
        }
      };
      final ToStringHelper helper = ObjectExtensions.<ToStringHelper>operator_doubleArrow(_stringHelper, _function);
      String _string = helper.toString();
      _xblockexpression = (_string);
    }
    return _xblockexpression;
  }
}
