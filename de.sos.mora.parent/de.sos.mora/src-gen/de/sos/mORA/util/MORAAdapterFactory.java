/**
 * generated by Xtext 2.19.0
 */
package de.sos.mORA.util;

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
import de.sos.mORA.MORAPackage;
import de.sos.mORA.Member;
import de.sos.mORA.Method;
import de.sos.mORA.Model;
import de.sos.mORA.Options;
import de.sos.mORA.Parameter;
import de.sos.mORA.PrimTypeDecl;
import de.sos.mORA.SingleTypeDecl;
import de.sos.mORA.StructDecl;
import de.sos.mORA.TypeDecl;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.sos.mORA.MORAPackage
 * @generated
 */
public class MORAAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MORAPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MORAAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = MORAPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MORASwitch<Adapter> modelSwitch =
    new MORASwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseInclude(Include object)
      {
        return createIncludeAdapter();
      }
      @Override
      public Adapter caseOptions(Options object)
      {
        return createOptionsAdapter();
      }
      @Override
      public Adapter caseJavaOptions(JavaOptions object)
      {
        return createJavaOptionsAdapter();
      }
      @Override
      public Adapter caseCSharpOptions(CSharpOptions object)
      {
        return createCSharpOptionsAdapter();
      }
      @Override
      public Adapter caseCppOptions(CppOptions object)
      {
        return createCppOptionsAdapter();
      }
      @Override
      public Adapter caseTypeDecl(TypeDecl object)
      {
        return createTypeDeclAdapter();
      }
      @Override
      public Adapter caseSingleTypeDecl(SingleTypeDecl object)
      {
        return createSingleTypeDeclAdapter();
      }
      @Override
      public Adapter caseAbstractType(AbstractType object)
      {
        return createAbstractTypeAdapter();
      }
      @Override
      public Adapter casePrimTypeDecl(PrimTypeDecl object)
      {
        return createPrimTypeDeclAdapter();
      }
      @Override
      public Adapter caseAnnotation(Annotation object)
      {
        return createAnnotationAdapter();
      }
      @Override
      public Adapter caseStructDecl(StructDecl object)
      {
        return createStructDeclAdapter();
      }
      @Override
      public Adapter caseMember(Member object)
      {
        return createMemberAdapter();
      }
      @Override
      public Adapter caseEnumDecl(EnumDecl object)
      {
        return createEnumDeclAdapter();
      }
      @Override
      public Adapter caseLiteral(Literal object)
      {
        return createLiteralAdapter();
      }
      @Override
      public Adapter caseListTypeDecl(ListTypeDecl object)
      {
        return createListTypeDeclAdapter();
      }
      @Override
      public Adapter caseInterface(Interface object)
      {
        return createInterfaceAdapter();
      }
      @Override
      public Adapter caseMethod(Method object)
      {
        return createMethodAdapter();
      }
      @Override
      public Adapter caseException(de.sos.mORA.Exception object)
      {
        return createExceptionAdapter();
      }
      @Override
      public Adapter caseParameter(Parameter object)
      {
        return createParameterAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Include <em>Include</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Include
   * @generated
   */
  public Adapter createIncludeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Options <em>Options</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Options
   * @generated
   */
  public Adapter createOptionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.JavaOptions <em>Java Options</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.JavaOptions
   * @generated
   */
  public Adapter createJavaOptionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.CSharpOptions <em>CSharp Options</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.CSharpOptions
   * @generated
   */
  public Adapter createCSharpOptionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.CppOptions <em>Cpp Options</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.CppOptions
   * @generated
   */
  public Adapter createCppOptionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.TypeDecl <em>Type Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.TypeDecl
   * @generated
   */
  public Adapter createTypeDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.SingleTypeDecl <em>Single Type Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.SingleTypeDecl
   * @generated
   */
  public Adapter createSingleTypeDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.AbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.AbstractType
   * @generated
   */
  public Adapter createAbstractTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.PrimTypeDecl <em>Prim Type Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.PrimTypeDecl
   * @generated
   */
  public Adapter createPrimTypeDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Annotation
   * @generated
   */
  public Adapter createAnnotationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.StructDecl <em>Struct Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.StructDecl
   * @generated
   */
  public Adapter createStructDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Member <em>Member</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Member
   * @generated
   */
  public Adapter createMemberAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.EnumDecl <em>Enum Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.EnumDecl
   * @generated
   */
  public Adapter createEnumDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Literal
   * @generated
   */
  public Adapter createLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.ListTypeDecl <em>List Type Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.ListTypeDecl
   * @generated
   */
  public Adapter createListTypeDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Interface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Interface
   * @generated
   */
  public Adapter createInterfaceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Method <em>Method</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Method
   * @generated
   */
  public Adapter createMethodAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Exception <em>Exception</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Exception
   * @generated
   */
  public Adapter createExceptionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.sos.mORA.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.sos.mORA.Parameter
   * @generated
   */
  public Adapter createParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //MORAAdapterFactory
