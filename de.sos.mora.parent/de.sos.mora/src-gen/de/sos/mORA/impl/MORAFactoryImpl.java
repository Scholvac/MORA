/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA.impl;

import de.sos.mORA.AbstractType;
import de.sos.mORA.Annotation;
import de.sos.mORA.CSharpOptions;
import de.sos.mORA.CppOptions;
import de.sos.mORA.EnumDecl;
import de.sos.mORA.Include;
import de.sos.mORA.Interface;
import de.sos.mORA.JavaOptions;
import de.sos.mORA.ListTypeDecl;
import de.sos.mORA.Literal;
import de.sos.mORA.MORAFactory;
import de.sos.mORA.MORAPackage;
import de.sos.mORA.Member;
import de.sos.mORA.Method;
import de.sos.mORA.Model;
import de.sos.mORA.Options;
import de.sos.mORA.Parameter;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.PrimTypeLiteral;
import de.sos.mORA.SingleTypeDecl;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MORAFactoryImpl extends EFactoryImpl implements MORAFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MORAFactory init()
  {
    try
    {
      MORAFactory theMORAFactory = (MORAFactory)EPackage.Registry.INSTANCE.getEFactory(MORAPackage.eNS_URI);
      if (theMORAFactory != null)
      {
        return theMORAFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MORAFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MORAFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MORAPackage.MODEL: return createModel();
      case MORAPackage.INCLUDE: return createInclude();
      case MORAPackage.OPTIONS: return createOptions();
      case MORAPackage.JAVA_OPTIONS: return createJavaOptions();
      case MORAPackage.CSHARP_OPTIONS: return createCSharpOptions();
      case MORAPackage.CPP_OPTIONS: return createCppOptions();
      case MORAPackage.TYPE_DECL: return createTypeDecl();
      case MORAPackage.SINGLE_TYPE_DECL: return createSingleTypeDecl();
      case MORAPackage.ABSTRACT_TYPE: return createAbstractType();
      case MORAPackage.PRIM_TYPE_DECL: return createPrimTypeDecl();
      case MORAPackage.ANNOTATION: return createAnnotation();
      case MORAPackage.STRUCT_DECL: return createStructDecl();
      case MORAPackage.MEMBER: return createMember();
      case MORAPackage.ENUM_DECL: return createEnumDecl();
      case MORAPackage.LITERAL: return createLiteral();
      case MORAPackage.LIST_TYPE_DECL: return createListTypeDecl();
      case MORAPackage.INTERFACE: return createInterface();
      case MORAPackage.METHOD: return createMethod();
      case MORAPackage.EXCEPTION: return createException();
      case MORAPackage.PARAMETER: return createParameter();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MORAPackage.PRIM_TYPE_LITERAL:
        return createPrimTypeLiteralFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MORAPackage.PRIM_TYPE_LITERAL:
        return convertPrimTypeLiteralToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Include createInclude()
  {
    IncludeImpl include = new IncludeImpl();
    return include;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Options createOptions()
  {
    OptionsImpl options = new OptionsImpl();
    return options;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JavaOptions createJavaOptions()
  {
    JavaOptionsImpl javaOptions = new JavaOptionsImpl();
    return javaOptions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CSharpOptions createCSharpOptions()
  {
    CSharpOptionsImpl cSharpOptions = new CSharpOptionsImpl();
    return cSharpOptions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CppOptions createCppOptions()
  {
    CppOptionsImpl cppOptions = new CppOptionsImpl();
    return cppOptions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeDecl createTypeDecl()
  {
    TypeDeclImpl typeDecl = new TypeDeclImpl();
    return typeDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SingleTypeDecl createSingleTypeDecl()
  {
    SingleTypeDeclImpl singleTypeDecl = new SingleTypeDeclImpl();
    return singleTypeDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractType createAbstractType()
  {
    AbstractTypeImpl abstractType = new AbstractTypeImpl();
    return abstractType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrimTypeDecl createPrimTypeDecl()
  {
    PrimTypeDeclImpl primTypeDecl = new PrimTypeDeclImpl();
    return primTypeDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation createAnnotation()
  {
    AnnotationImpl annotation = new AnnotationImpl();
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructDecl createStructDecl()
  {
    StructDeclImpl structDecl = new StructDeclImpl();
    return structDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Member createMember()
  {
    MemberImpl member = new MemberImpl();
    return member;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumDecl createEnumDecl()
  {
    EnumDeclImpl enumDecl = new EnumDeclImpl();
    return enumDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Literal createLiteral()
  {
    LiteralImpl literal = new LiteralImpl();
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ListTypeDecl createListTypeDecl()
  {
    ListTypeDeclImpl listTypeDecl = new ListTypeDeclImpl();
    return listTypeDecl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Interface createInterface()
  {
    InterfaceImpl interface_ = new InterfaceImpl();
    return interface_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method createMethod()
  {
    MethodImpl method = new MethodImpl();
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public de.sos.mORA.Exception createException()
  {
    ExceptionImpl exception = new ExceptionImpl();
    return exception;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrimTypeLiteral createPrimTypeLiteralFromString(EDataType eDataType, String initialValue)
  {
    PrimTypeLiteral result = PrimTypeLiteral.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertPrimTypeLiteralToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MORAPackage getMORAPackage()
  {
    return (MORAPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MORAPackage getPackage()
  {
    return MORAPackage.eINSTANCE;
  }

} //MORAFactoryImpl
