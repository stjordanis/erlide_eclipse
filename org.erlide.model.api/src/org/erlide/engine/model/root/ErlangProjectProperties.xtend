package org.erlide.engine.model.root

import com.google.common.base.Objects
import com.google.common.collect.Lists
import java.util.Collection
import java.util.Collections
import java.util.List
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import org.erlide.runtime.runtimeinfo.RuntimeVersion

class ErlangProjectProperties {

	@Property IPath outputDir
	@Property Collection<IPath> sourceDirs
	@Property Collection<IPath> includeDirs

	@Property RuntimeVersion requiredRuntimeVersion

	// TODO these have to be handled better! Assembla #1311
	@Property String externalIncludesFile
	@Property String externalModulesFile

	val public static ErlangProjectProperties DEFAULT = new ErlangProjectProperties() => [
		_sourceDirs = PathSerializer.unpackList(ProjectPreferencesConstants.DEFAULT_SOURCE_DIRS)
		_outputDir = new Path(ProjectPreferencesConstants.DEFAULT_OUTPUT_DIR)
		_includeDirs = PathSerializer.unpackList(ProjectPreferencesConstants.DEFAULT_INCLUDE_DIRS)
		_externalIncludesFile = ProjectPreferencesConstants.DEFAULT_EXTERNAL_INCLUDES
		_externalModulesFile = ProjectPreferencesConstants.DEFAULT_EXTERNAL_MODULES
		_requiredRuntimeVersion = ProjectPreferencesConstants.DEFAULT_RUNTIME_VERSION
	]

	new() {
		_sourceDirs = newArrayList()
		_outputDir = new Path("")
		_includeDirs = newArrayList()
		_externalIncludesFile = ""
		_externalModulesFile = ""
		_requiredRuntimeVersion = ProjectPreferencesConstants.DEFAULT_RUNTIME_VERSION
	}

	def void setIncludeDirs(Collection<IPath> dirs) {
		_includeDirs = Lists.newArrayList(dirs)
	}

	def void setIncludeDirs(IPath... dirs) {
		_includeDirs = Lists.newArrayList(dirs)
	}

	def void setSourceDirs(Collection<IPath> dirs) {
		_sourceDirs = Lists.newArrayList(dirs)
	}

	def void setSourceDirs(IPath... dirs) {
		_sourceDirs = Lists.newArrayList(dirs)
	}

	def void copyFrom(ErlangProjectProperties props) {
		_includeDirs = props._includeDirs
		_sourceDirs = props._sourceDirs
		_outputDir = props._outputDir
		_requiredRuntimeVersion = props._requiredRuntimeVersion
		_externalIncludesFile = props._externalIncludesFile
		_externalModulesFile = props._externalModulesFile
	}

	def boolean sameAs(Object other1) {
		if (this === other1)
			return true
		if (other1 === null)
			return false
		if (!(other1 instanceof ErlangProjectProperties))
			return false
		val other = other1 as ErlangProjectProperties
		if (_outputDir === null) {
			if (other._outputDir !== null)
				return false
		} else if (!_outputDir.equals(other._outputDir))
			return false
		if (_sourceDirs === null) {
			if (other._sourceDirs !== null)
				return false
		} else if (!_sourceDirs.equals(other._sourceDirs))
			return false
		if (_includeDirs === null) {
			if (other._includeDirs !== null)
				return false
		} else if (!_includeDirs.equals(other._includeDirs))
			return false
		if (_externalIncludesFile === null) {
			if (other._externalIncludesFile !== null)
				return false
		} else if (!_externalIncludesFile.equals(other._externalIncludesFile))
			return false
		if (_externalModulesFile === null) {
			if (other._externalModulesFile !== null)
				return false
		} else if (!_externalModulesFile.equals(other._externalModulesFile))
			return false
		if (_requiredRuntimeVersion === null) {
			if (other._requiredRuntimeVersion !== null)
				return false
		} else if (!_requiredRuntimeVersion.equals(other._requiredRuntimeVersion))
			return false
		return true
	}

	override toString() {
		val helper = Objects.toStringHelper(this) => [
			add("outputDir", _outputDir)
			add("sources", _sourceDirs)
			add("includes", _includeDirs)
			add("runtimeVersion", _requiredRuntimeVersion)
		]
		helper.toString
	}

	def ErlangProjectProperties resolve() {
		val result = new ErlangProjectProperties()
		val dflt = ErlangProjectProperties.DEFAULT
		result => [
			copyFrom(this)
			if (getOutputDir() === null) {
				setOutputDir(dflt.getOutputDir())
			}
			setOutputDir(resolvePath(getOutputDir()))
			if (getSourceDirs() === null) {
				setSourceDirs(dflt.getSourceDirs())
			}
			setSourceDirs(resolvePaths(getSourceDirs()))
			if (getIncludeDirs() === null) {
				setIncludeDirs(dflt.getIncludeDirs())
			}
			setIncludeDirs(resolvePaths(getIncludeDirs()))
		]
		return result;
	}

	def private Collection<IPath> resolvePaths(Collection<IPath> paths) {
		val pathVariableManager = ResourcesPlugin.getWorkspace().getPathVariableManager()
		val List<IPath> result = Lists.newArrayListWithCapacity(paths.size())
		for (IPath path : paths) {
			val resolvedPath = pathVariableManager.resolvePath(path)
			result.add(resolvedPath)
		}
		return Collections.unmodifiableCollection(result)
	}

	def private IPath resolvePath(IPath path) {
		val pathVariableManager = ResourcesPlugin.getWorkspace().getPathVariableManager()
		return pathVariableManager.resolvePath(path)
	}

}
