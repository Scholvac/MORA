package de.sos.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import de.sos.services.MORAGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMORAParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_INT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.'", "'::'", "'bool'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'string'", "'void'", "'package'", "'{'", "'}'", "';'", "'import'", "'options'", "'java'", "'base-package'", "'='", "'csharp'", "'base-namespace'", "'cpp'", "'@'", "'struct'", "'enum'", "'List'", "'<'", "'>'", "'interface'", "'extends'", "','", "'('", "')'", "'*'", "'throws'", "'exception'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=6;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalMORAParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMORAParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMORAParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMORA.g"; }


    	private MORAGrammarAccess grammarAccess;

    	public void setGrammarAccess(MORAGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalMORA.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalMORA.g:54:1: ( ruleModel EOF )
            // InternalMORA.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMORA.g:62:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:66:2: ( ( ( rule__Model__Group__0 ) ) )
            // InternalMORA.g:67:2: ( ( rule__Model__Group__0 ) )
            {
            // InternalMORA.g:67:2: ( ( rule__Model__Group__0 ) )
            // InternalMORA.g:68:3: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // InternalMORA.g:69:3: ( rule__Model__Group__0 )
            // InternalMORA.g:69:4: rule__Model__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleInclude"
    // InternalMORA.g:78:1: entryRuleInclude : ruleInclude EOF ;
    public final void entryRuleInclude() throws RecognitionException {
        try {
            // InternalMORA.g:79:1: ( ruleInclude EOF )
            // InternalMORA.g:80:1: ruleInclude EOF
            {
             before(grammarAccess.getIncludeRule()); 
            pushFollow(FOLLOW_1);
            ruleInclude();

            state._fsp--;

             after(grammarAccess.getIncludeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInclude"


    // $ANTLR start "ruleInclude"
    // InternalMORA.g:87:1: ruleInclude : ( ( rule__Include__Group__0 ) ) ;
    public final void ruleInclude() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:91:2: ( ( ( rule__Include__Group__0 ) ) )
            // InternalMORA.g:92:2: ( ( rule__Include__Group__0 ) )
            {
            // InternalMORA.g:92:2: ( ( rule__Include__Group__0 ) )
            // InternalMORA.g:93:3: ( rule__Include__Group__0 )
            {
             before(grammarAccess.getIncludeAccess().getGroup()); 
            // InternalMORA.g:94:3: ( rule__Include__Group__0 )
            // InternalMORA.g:94:4: rule__Include__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Include__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIncludeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInclude"


    // $ANTLR start "entryRuleOptions"
    // InternalMORA.g:103:1: entryRuleOptions : ruleOptions EOF ;
    public final void entryRuleOptions() throws RecognitionException {
        try {
            // InternalMORA.g:104:1: ( ruleOptions EOF )
            // InternalMORA.g:105:1: ruleOptions EOF
            {
             before(grammarAccess.getOptionsRule()); 
            pushFollow(FOLLOW_1);
            ruleOptions();

            state._fsp--;

             after(grammarAccess.getOptionsRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOptions"


    // $ANTLR start "ruleOptions"
    // InternalMORA.g:112:1: ruleOptions : ( ( rule__Options__Group__0 ) ) ;
    public final void ruleOptions() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:116:2: ( ( ( rule__Options__Group__0 ) ) )
            // InternalMORA.g:117:2: ( ( rule__Options__Group__0 ) )
            {
            // InternalMORA.g:117:2: ( ( rule__Options__Group__0 ) )
            // InternalMORA.g:118:3: ( rule__Options__Group__0 )
            {
             before(grammarAccess.getOptionsAccess().getGroup()); 
            // InternalMORA.g:119:3: ( rule__Options__Group__0 )
            // InternalMORA.g:119:4: rule__Options__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Options__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptionsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptions"


    // $ANTLR start "entryRuleJavaOptions"
    // InternalMORA.g:128:1: entryRuleJavaOptions : ruleJavaOptions EOF ;
    public final void entryRuleJavaOptions() throws RecognitionException {
        try {
            // InternalMORA.g:129:1: ( ruleJavaOptions EOF )
            // InternalMORA.g:130:1: ruleJavaOptions EOF
            {
             before(grammarAccess.getJavaOptionsRule()); 
            pushFollow(FOLLOW_1);
            ruleJavaOptions();

            state._fsp--;

             after(grammarAccess.getJavaOptionsRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJavaOptions"


    // $ANTLR start "ruleJavaOptions"
    // InternalMORA.g:137:1: ruleJavaOptions : ( ( rule__JavaOptions__Group__0 ) ) ;
    public final void ruleJavaOptions() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:141:2: ( ( ( rule__JavaOptions__Group__0 ) ) )
            // InternalMORA.g:142:2: ( ( rule__JavaOptions__Group__0 ) )
            {
            // InternalMORA.g:142:2: ( ( rule__JavaOptions__Group__0 ) )
            // InternalMORA.g:143:3: ( rule__JavaOptions__Group__0 )
            {
             before(grammarAccess.getJavaOptionsAccess().getGroup()); 
            // InternalMORA.g:144:3: ( rule__JavaOptions__Group__0 )
            // InternalMORA.g:144:4: rule__JavaOptions__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getJavaOptionsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJavaOptions"


    // $ANTLR start "entryRuleCSharpOptions"
    // InternalMORA.g:153:1: entryRuleCSharpOptions : ruleCSharpOptions EOF ;
    public final void entryRuleCSharpOptions() throws RecognitionException {
        try {
            // InternalMORA.g:154:1: ( ruleCSharpOptions EOF )
            // InternalMORA.g:155:1: ruleCSharpOptions EOF
            {
             before(grammarAccess.getCSharpOptionsRule()); 
            pushFollow(FOLLOW_1);
            ruleCSharpOptions();

            state._fsp--;

             after(grammarAccess.getCSharpOptionsRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCSharpOptions"


    // $ANTLR start "ruleCSharpOptions"
    // InternalMORA.g:162:1: ruleCSharpOptions : ( ( rule__CSharpOptions__Group__0 ) ) ;
    public final void ruleCSharpOptions() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:166:2: ( ( ( rule__CSharpOptions__Group__0 ) ) )
            // InternalMORA.g:167:2: ( ( rule__CSharpOptions__Group__0 ) )
            {
            // InternalMORA.g:167:2: ( ( rule__CSharpOptions__Group__0 ) )
            // InternalMORA.g:168:3: ( rule__CSharpOptions__Group__0 )
            {
             before(grammarAccess.getCSharpOptionsAccess().getGroup()); 
            // InternalMORA.g:169:3: ( rule__CSharpOptions__Group__0 )
            // InternalMORA.g:169:4: rule__CSharpOptions__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCSharpOptionsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCSharpOptions"


    // $ANTLR start "entryRuleCppOptions"
    // InternalMORA.g:178:1: entryRuleCppOptions : ruleCppOptions EOF ;
    public final void entryRuleCppOptions() throws RecognitionException {
        try {
            // InternalMORA.g:179:1: ( ruleCppOptions EOF )
            // InternalMORA.g:180:1: ruleCppOptions EOF
            {
             before(grammarAccess.getCppOptionsRule()); 
            pushFollow(FOLLOW_1);
            ruleCppOptions();

            state._fsp--;

             after(grammarAccess.getCppOptionsRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCppOptions"


    // $ANTLR start "ruleCppOptions"
    // InternalMORA.g:187:1: ruleCppOptions : ( ( rule__CppOptions__Group__0 ) ) ;
    public final void ruleCppOptions() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:191:2: ( ( ( rule__CppOptions__Group__0 ) ) )
            // InternalMORA.g:192:2: ( ( rule__CppOptions__Group__0 ) )
            {
            // InternalMORA.g:192:2: ( ( rule__CppOptions__Group__0 ) )
            // InternalMORA.g:193:3: ( rule__CppOptions__Group__0 )
            {
             before(grammarAccess.getCppOptionsAccess().getGroup()); 
            // InternalMORA.g:194:3: ( rule__CppOptions__Group__0 )
            // InternalMORA.g:194:4: rule__CppOptions__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCppOptionsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCppOptions"


    // $ANTLR start "entryRuleTypeDecl"
    // InternalMORA.g:203:1: entryRuleTypeDecl : ruleTypeDecl EOF ;
    public final void entryRuleTypeDecl() throws RecognitionException {
        try {
            // InternalMORA.g:204:1: ( ruleTypeDecl EOF )
            // InternalMORA.g:205:1: ruleTypeDecl EOF
            {
             before(grammarAccess.getTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            ruleTypeDecl();

            state._fsp--;

             after(grammarAccess.getTypeDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTypeDecl"


    // $ANTLR start "ruleTypeDecl"
    // InternalMORA.g:212:1: ruleTypeDecl : ( ( rule__TypeDecl__Alternatives ) ) ;
    public final void ruleTypeDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:216:2: ( ( ( rule__TypeDecl__Alternatives ) ) )
            // InternalMORA.g:217:2: ( ( rule__TypeDecl__Alternatives ) )
            {
            // InternalMORA.g:217:2: ( ( rule__TypeDecl__Alternatives ) )
            // InternalMORA.g:218:3: ( rule__TypeDecl__Alternatives )
            {
             before(grammarAccess.getTypeDeclAccess().getAlternatives()); 
            // InternalMORA.g:219:3: ( rule__TypeDecl__Alternatives )
            // InternalMORA.g:219:4: rule__TypeDecl__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__TypeDecl__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getTypeDeclAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTypeDecl"


    // $ANTLR start "entryRuleSingleTypeDecl"
    // InternalMORA.g:228:1: entryRuleSingleTypeDecl : ruleSingleTypeDecl EOF ;
    public final void entryRuleSingleTypeDecl() throws RecognitionException {
        try {
            // InternalMORA.g:229:1: ( ruleSingleTypeDecl EOF )
            // InternalMORA.g:230:1: ruleSingleTypeDecl EOF
            {
             before(grammarAccess.getSingleTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            ruleSingleTypeDecl();

            state._fsp--;

             after(grammarAccess.getSingleTypeDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSingleTypeDecl"


    // $ANTLR start "ruleSingleTypeDecl"
    // InternalMORA.g:237:1: ruleSingleTypeDecl : ( ( rule__SingleTypeDecl__Alternatives ) ) ;
    public final void ruleSingleTypeDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:241:2: ( ( ( rule__SingleTypeDecl__Alternatives ) ) )
            // InternalMORA.g:242:2: ( ( rule__SingleTypeDecl__Alternatives ) )
            {
            // InternalMORA.g:242:2: ( ( rule__SingleTypeDecl__Alternatives ) )
            // InternalMORA.g:243:3: ( rule__SingleTypeDecl__Alternatives )
            {
             before(grammarAccess.getSingleTypeDeclAccess().getAlternatives()); 
            // InternalMORA.g:244:3: ( rule__SingleTypeDecl__Alternatives )
            // InternalMORA.g:244:4: rule__SingleTypeDecl__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__SingleTypeDecl__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSingleTypeDeclAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSingleTypeDecl"


    // $ANTLR start "entryRulePrimTypeDecl"
    // InternalMORA.g:253:1: entryRulePrimTypeDecl : rulePrimTypeDecl EOF ;
    public final void entryRulePrimTypeDecl() throws RecognitionException {
        try {
            // InternalMORA.g:254:1: ( rulePrimTypeDecl EOF )
            // InternalMORA.g:255:1: rulePrimTypeDecl EOF
            {
             before(grammarAccess.getPrimTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            rulePrimTypeDecl();

            state._fsp--;

             after(grammarAccess.getPrimTypeDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimTypeDecl"


    // $ANTLR start "rulePrimTypeDecl"
    // InternalMORA.g:262:1: rulePrimTypeDecl : ( ( rule__PrimTypeDecl__NameAssignment ) ) ;
    public final void rulePrimTypeDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:266:2: ( ( ( rule__PrimTypeDecl__NameAssignment ) ) )
            // InternalMORA.g:267:2: ( ( rule__PrimTypeDecl__NameAssignment ) )
            {
            // InternalMORA.g:267:2: ( ( rule__PrimTypeDecl__NameAssignment ) )
            // InternalMORA.g:268:3: ( rule__PrimTypeDecl__NameAssignment )
            {
             before(grammarAccess.getPrimTypeDeclAccess().getNameAssignment()); 
            // InternalMORA.g:269:3: ( rule__PrimTypeDecl__NameAssignment )
            // InternalMORA.g:269:4: rule__PrimTypeDecl__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__PrimTypeDecl__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getPrimTypeDeclAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimTypeDecl"


    // $ANTLR start "entryRuleAnnotation"
    // InternalMORA.g:278:1: entryRuleAnnotation : ruleAnnotation EOF ;
    public final void entryRuleAnnotation() throws RecognitionException {
        try {
            // InternalMORA.g:279:1: ( ruleAnnotation EOF )
            // InternalMORA.g:280:1: ruleAnnotation EOF
            {
             before(grammarAccess.getAnnotationRule()); 
            pushFollow(FOLLOW_1);
            ruleAnnotation();

            state._fsp--;

             after(grammarAccess.getAnnotationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // InternalMORA.g:287:1: ruleAnnotation : ( ( rule__Annotation__Group__0 ) ) ;
    public final void ruleAnnotation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:291:2: ( ( ( rule__Annotation__Group__0 ) ) )
            // InternalMORA.g:292:2: ( ( rule__Annotation__Group__0 ) )
            {
            // InternalMORA.g:292:2: ( ( rule__Annotation__Group__0 ) )
            // InternalMORA.g:293:3: ( rule__Annotation__Group__0 )
            {
             before(grammarAccess.getAnnotationAccess().getGroup()); 
            // InternalMORA.g:294:3: ( rule__Annotation__Group__0 )
            // InternalMORA.g:294:4: rule__Annotation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Annotation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAnnotationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleStructDecl"
    // InternalMORA.g:303:1: entryRuleStructDecl : ruleStructDecl EOF ;
    public final void entryRuleStructDecl() throws RecognitionException {
        try {
            // InternalMORA.g:304:1: ( ruleStructDecl EOF )
            // InternalMORA.g:305:1: ruleStructDecl EOF
            {
             before(grammarAccess.getStructDeclRule()); 
            pushFollow(FOLLOW_1);
            ruleStructDecl();

            state._fsp--;

             after(grammarAccess.getStructDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStructDecl"


    // $ANTLR start "ruleStructDecl"
    // InternalMORA.g:312:1: ruleStructDecl : ( ( rule__StructDecl__Group__0 ) ) ;
    public final void ruleStructDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:316:2: ( ( ( rule__StructDecl__Group__0 ) ) )
            // InternalMORA.g:317:2: ( ( rule__StructDecl__Group__0 ) )
            {
            // InternalMORA.g:317:2: ( ( rule__StructDecl__Group__0 ) )
            // InternalMORA.g:318:3: ( rule__StructDecl__Group__0 )
            {
             before(grammarAccess.getStructDeclAccess().getGroup()); 
            // InternalMORA.g:319:3: ( rule__StructDecl__Group__0 )
            // InternalMORA.g:319:4: rule__StructDecl__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getStructDeclAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStructDecl"


    // $ANTLR start "entryRuleMember"
    // InternalMORA.g:328:1: entryRuleMember : ruleMember EOF ;
    public final void entryRuleMember() throws RecognitionException {
        try {
            // InternalMORA.g:329:1: ( ruleMember EOF )
            // InternalMORA.g:330:1: ruleMember EOF
            {
             before(grammarAccess.getMemberRule()); 
            pushFollow(FOLLOW_1);
            ruleMember();

            state._fsp--;

             after(grammarAccess.getMemberRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMember"


    // $ANTLR start "ruleMember"
    // InternalMORA.g:337:1: ruleMember : ( ( rule__Member__Group__0 ) ) ;
    public final void ruleMember() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:341:2: ( ( ( rule__Member__Group__0 ) ) )
            // InternalMORA.g:342:2: ( ( rule__Member__Group__0 ) )
            {
            // InternalMORA.g:342:2: ( ( rule__Member__Group__0 ) )
            // InternalMORA.g:343:3: ( rule__Member__Group__0 )
            {
             before(grammarAccess.getMemberAccess().getGroup()); 
            // InternalMORA.g:344:3: ( rule__Member__Group__0 )
            // InternalMORA.g:344:4: rule__Member__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Member__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMemberAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMember"


    // $ANTLR start "entryRuleEnumDecl"
    // InternalMORA.g:353:1: entryRuleEnumDecl : ruleEnumDecl EOF ;
    public final void entryRuleEnumDecl() throws RecognitionException {
        try {
            // InternalMORA.g:354:1: ( ruleEnumDecl EOF )
            // InternalMORA.g:355:1: ruleEnumDecl EOF
            {
             before(grammarAccess.getEnumDeclRule()); 
            pushFollow(FOLLOW_1);
            ruleEnumDecl();

            state._fsp--;

             after(grammarAccess.getEnumDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEnumDecl"


    // $ANTLR start "ruleEnumDecl"
    // InternalMORA.g:362:1: ruleEnumDecl : ( ( rule__EnumDecl__Group__0 ) ) ;
    public final void ruleEnumDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:366:2: ( ( ( rule__EnumDecl__Group__0 ) ) )
            // InternalMORA.g:367:2: ( ( rule__EnumDecl__Group__0 ) )
            {
            // InternalMORA.g:367:2: ( ( rule__EnumDecl__Group__0 ) )
            // InternalMORA.g:368:3: ( rule__EnumDecl__Group__0 )
            {
             before(grammarAccess.getEnumDeclAccess().getGroup()); 
            // InternalMORA.g:369:3: ( rule__EnumDecl__Group__0 )
            // InternalMORA.g:369:4: rule__EnumDecl__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEnumDeclAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEnumDecl"


    // $ANTLR start "entryRuleLiteral"
    // InternalMORA.g:378:1: entryRuleLiteral : ruleLiteral EOF ;
    public final void entryRuleLiteral() throws RecognitionException {
        try {
            // InternalMORA.g:379:1: ( ruleLiteral EOF )
            // InternalMORA.g:380:1: ruleLiteral EOF
            {
             before(grammarAccess.getLiteralRule()); 
            pushFollow(FOLLOW_1);
            ruleLiteral();

            state._fsp--;

             after(grammarAccess.getLiteralRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalMORA.g:387:1: ruleLiteral : ( ( rule__Literal__Group__0 ) ) ;
    public final void ruleLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:391:2: ( ( ( rule__Literal__Group__0 ) ) )
            // InternalMORA.g:392:2: ( ( rule__Literal__Group__0 ) )
            {
            // InternalMORA.g:392:2: ( ( rule__Literal__Group__0 ) )
            // InternalMORA.g:393:3: ( rule__Literal__Group__0 )
            {
             before(grammarAccess.getLiteralAccess().getGroup()); 
            // InternalMORA.g:394:3: ( rule__Literal__Group__0 )
            // InternalMORA.g:394:4: rule__Literal__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLiteralAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleListTypeDecl"
    // InternalMORA.g:403:1: entryRuleListTypeDecl : ruleListTypeDecl EOF ;
    public final void entryRuleListTypeDecl() throws RecognitionException {
        try {
            // InternalMORA.g:404:1: ( ruleListTypeDecl EOF )
            // InternalMORA.g:405:1: ruleListTypeDecl EOF
            {
             before(grammarAccess.getListTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            ruleListTypeDecl();

            state._fsp--;

             after(grammarAccess.getListTypeDeclRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleListTypeDecl"


    // $ANTLR start "ruleListTypeDecl"
    // InternalMORA.g:412:1: ruleListTypeDecl : ( ( rule__ListTypeDecl__Group__0 ) ) ;
    public final void ruleListTypeDecl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:416:2: ( ( ( rule__ListTypeDecl__Group__0 ) ) )
            // InternalMORA.g:417:2: ( ( rule__ListTypeDecl__Group__0 ) )
            {
            // InternalMORA.g:417:2: ( ( rule__ListTypeDecl__Group__0 ) )
            // InternalMORA.g:418:3: ( rule__ListTypeDecl__Group__0 )
            {
             before(grammarAccess.getListTypeDeclAccess().getGroup()); 
            // InternalMORA.g:419:3: ( rule__ListTypeDecl__Group__0 )
            // InternalMORA.g:419:4: rule__ListTypeDecl__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getListTypeDeclAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleListTypeDecl"


    // $ANTLR start "entryRuleInterface"
    // InternalMORA.g:428:1: entryRuleInterface : ruleInterface EOF ;
    public final void entryRuleInterface() throws RecognitionException {
        try {
            // InternalMORA.g:429:1: ( ruleInterface EOF )
            // InternalMORA.g:430:1: ruleInterface EOF
            {
             before(grammarAccess.getInterfaceRule()); 
            pushFollow(FOLLOW_1);
            ruleInterface();

            state._fsp--;

             after(grammarAccess.getInterfaceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleInterface"


    // $ANTLR start "ruleInterface"
    // InternalMORA.g:437:1: ruleInterface : ( ( rule__Interface__Group__0 ) ) ;
    public final void ruleInterface() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:441:2: ( ( ( rule__Interface__Group__0 ) ) )
            // InternalMORA.g:442:2: ( ( rule__Interface__Group__0 ) )
            {
            // InternalMORA.g:442:2: ( ( rule__Interface__Group__0 ) )
            // InternalMORA.g:443:3: ( rule__Interface__Group__0 )
            {
             before(grammarAccess.getInterfaceAccess().getGroup()); 
            // InternalMORA.g:444:3: ( rule__Interface__Group__0 )
            // InternalMORA.g:444:4: rule__Interface__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Interface__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getInterfaceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleInterface"


    // $ANTLR start "entryRuleMethod"
    // InternalMORA.g:453:1: entryRuleMethod : ruleMethod EOF ;
    public final void entryRuleMethod() throws RecognitionException {
        try {
            // InternalMORA.g:454:1: ( ruleMethod EOF )
            // InternalMORA.g:455:1: ruleMethod EOF
            {
             before(grammarAccess.getMethodRule()); 
            pushFollow(FOLLOW_1);
            ruleMethod();

            state._fsp--;

             after(grammarAccess.getMethodRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // InternalMORA.g:462:1: ruleMethod : ( ( rule__Method__Group__0 ) ) ;
    public final void ruleMethod() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:466:2: ( ( ( rule__Method__Group__0 ) ) )
            // InternalMORA.g:467:2: ( ( rule__Method__Group__0 ) )
            {
            // InternalMORA.g:467:2: ( ( rule__Method__Group__0 ) )
            // InternalMORA.g:468:3: ( rule__Method__Group__0 )
            {
             before(grammarAccess.getMethodAccess().getGroup()); 
            // InternalMORA.g:469:3: ( rule__Method__Group__0 )
            // InternalMORA.g:469:4: rule__Method__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleException"
    // InternalMORA.g:478:1: entryRuleException : ruleException EOF ;
    public final void entryRuleException() throws RecognitionException {
        try {
            // InternalMORA.g:479:1: ( ruleException EOF )
            // InternalMORA.g:480:1: ruleException EOF
            {
             before(grammarAccess.getExceptionRule()); 
            pushFollow(FOLLOW_1);
            ruleException();

            state._fsp--;

             after(grammarAccess.getExceptionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleException"


    // $ANTLR start "ruleException"
    // InternalMORA.g:487:1: ruleException : ( ( rule__Exception__Group__0 ) ) ;
    public final void ruleException() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:491:2: ( ( ( rule__Exception__Group__0 ) ) )
            // InternalMORA.g:492:2: ( ( rule__Exception__Group__0 ) )
            {
            // InternalMORA.g:492:2: ( ( rule__Exception__Group__0 ) )
            // InternalMORA.g:493:3: ( rule__Exception__Group__0 )
            {
             before(grammarAccess.getExceptionAccess().getGroup()); 
            // InternalMORA.g:494:3: ( rule__Exception__Group__0 )
            // InternalMORA.g:494:4: rule__Exception__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Exception__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExceptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleException"


    // $ANTLR start "entryRuleParameter"
    // InternalMORA.g:503:1: entryRuleParameter : ruleParameter EOF ;
    public final void entryRuleParameter() throws RecognitionException {
        try {
            // InternalMORA.g:504:1: ( ruleParameter EOF )
            // InternalMORA.g:505:1: ruleParameter EOF
            {
             before(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getParameterRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalMORA.g:512:1: ruleParameter : ( ( rule__Parameter__Group__0 ) ) ;
    public final void ruleParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:516:2: ( ( ( rule__Parameter__Group__0 ) ) )
            // InternalMORA.g:517:2: ( ( rule__Parameter__Group__0 ) )
            {
            // InternalMORA.g:517:2: ( ( rule__Parameter__Group__0 ) )
            // InternalMORA.g:518:3: ( rule__Parameter__Group__0 )
            {
             before(grammarAccess.getParameterAccess().getGroup()); 
            // InternalMORA.g:519:3: ( rule__Parameter__Group__0 )
            // InternalMORA.g:519:4: rule__Parameter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalMORA.g:528:1: entryRuleQualifiedName : ruleQualifiedName EOF ;
    public final void entryRuleQualifiedName() throws RecognitionException {
        try {
            // InternalMORA.g:529:1: ( ruleQualifiedName EOF )
            // InternalMORA.g:530:1: ruleQualifiedName EOF
            {
             before(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getQualifiedNameRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalMORA.g:537:1: ruleQualifiedName : ( ( rule__QualifiedName__Group__0 ) ) ;
    public final void ruleQualifiedName() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:541:2: ( ( ( rule__QualifiedName__Group__0 ) ) )
            // InternalMORA.g:542:2: ( ( rule__QualifiedName__Group__0 ) )
            {
            // InternalMORA.g:542:2: ( ( rule__QualifiedName__Group__0 ) )
            // InternalMORA.g:543:3: ( rule__QualifiedName__Group__0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup()); 
            // InternalMORA.g:544:3: ( rule__QualifiedName__Group__0 )
            // InternalMORA.g:544:4: rule__QualifiedName__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "rulePrimTypeLiteral"
    // InternalMORA.g:553:1: rulePrimTypeLiteral : ( ( rule__PrimTypeLiteral__Alternatives ) ) ;
    public final void rulePrimTypeLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:557:1: ( ( ( rule__PrimTypeLiteral__Alternatives ) ) )
            // InternalMORA.g:558:2: ( ( rule__PrimTypeLiteral__Alternatives ) )
            {
            // InternalMORA.g:558:2: ( ( rule__PrimTypeLiteral__Alternatives ) )
            // InternalMORA.g:559:3: ( rule__PrimTypeLiteral__Alternatives )
            {
             before(grammarAccess.getPrimTypeLiteralAccess().getAlternatives()); 
            // InternalMORA.g:560:3: ( rule__PrimTypeLiteral__Alternatives )
            // InternalMORA.g:560:4: rule__PrimTypeLiteral__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PrimTypeLiteral__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimTypeLiteralAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimTypeLiteral"


    // $ANTLR start "rule__Model__Alternatives_5_0"
    // InternalMORA.g:568:1: rule__Model__Alternatives_5_0 : ( ( ( rule__Model__InterfacesAssignment_5_0_0 ) ) | ( ( rule__Model__TypesAssignment_5_0_1 ) ) );
    public final void rule__Model__Alternatives_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:572:1: ( ( ( rule__Model__InterfacesAssignment_5_0_0 ) ) | ( ( rule__Model__TypesAssignment_5_0_1 ) ) )
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // InternalMORA.g:573:2: ( ( rule__Model__InterfacesAssignment_5_0_0 ) )
                    {
                    // InternalMORA.g:573:2: ( ( rule__Model__InterfacesAssignment_5_0_0 ) )
                    // InternalMORA.g:574:3: ( rule__Model__InterfacesAssignment_5_0_0 )
                    {
                     before(grammarAccess.getModelAccess().getInterfacesAssignment_5_0_0()); 
                    // InternalMORA.g:575:3: ( rule__Model__InterfacesAssignment_5_0_0 )
                    // InternalMORA.g:575:4: rule__Model__InterfacesAssignment_5_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__InterfacesAssignment_5_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getModelAccess().getInterfacesAssignment_5_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:579:2: ( ( rule__Model__TypesAssignment_5_0_1 ) )
                    {
                    // InternalMORA.g:579:2: ( ( rule__Model__TypesAssignment_5_0_1 ) )
                    // InternalMORA.g:580:3: ( rule__Model__TypesAssignment_5_0_1 )
                    {
                     before(grammarAccess.getModelAccess().getTypesAssignment_5_0_1()); 
                    // InternalMORA.g:581:3: ( rule__Model__TypesAssignment_5_0_1 )
                    // InternalMORA.g:581:4: rule__Model__TypesAssignment_5_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__TypesAssignment_5_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getModelAccess().getTypesAssignment_5_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Alternatives_5_0"


    // $ANTLR start "rule__Options__Alternatives_2"
    // InternalMORA.g:589:1: rule__Options__Alternatives_2 : ( ( ( rule__Options__JavaOptionsAssignment_2_0 ) ) | ( ( rule__Options__CsOptionsAssignment_2_1 ) ) | ( ( rule__Options__CppOptionsAssignment_2_2 ) ) );
    public final void rule__Options__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:593:1: ( ( ( rule__Options__JavaOptionsAssignment_2_0 ) ) | ( ( rule__Options__CsOptionsAssignment_2_1 ) ) | ( ( rule__Options__CppOptionsAssignment_2_2 ) ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt2=1;
                }
                break;
            case 31:
                {
                alt2=2;
                }
                break;
            case 33:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalMORA.g:594:2: ( ( rule__Options__JavaOptionsAssignment_2_0 ) )
                    {
                    // InternalMORA.g:594:2: ( ( rule__Options__JavaOptionsAssignment_2_0 ) )
                    // InternalMORA.g:595:3: ( rule__Options__JavaOptionsAssignment_2_0 )
                    {
                     before(grammarAccess.getOptionsAccess().getJavaOptionsAssignment_2_0()); 
                    // InternalMORA.g:596:3: ( rule__Options__JavaOptionsAssignment_2_0 )
                    // InternalMORA.g:596:4: rule__Options__JavaOptionsAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Options__JavaOptionsAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getOptionsAccess().getJavaOptionsAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:600:2: ( ( rule__Options__CsOptionsAssignment_2_1 ) )
                    {
                    // InternalMORA.g:600:2: ( ( rule__Options__CsOptionsAssignment_2_1 ) )
                    // InternalMORA.g:601:3: ( rule__Options__CsOptionsAssignment_2_1 )
                    {
                     before(grammarAccess.getOptionsAccess().getCsOptionsAssignment_2_1()); 
                    // InternalMORA.g:602:3: ( rule__Options__CsOptionsAssignment_2_1 )
                    // InternalMORA.g:602:4: rule__Options__CsOptionsAssignment_2_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Options__CsOptionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getOptionsAccess().getCsOptionsAssignment_2_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:606:2: ( ( rule__Options__CppOptionsAssignment_2_2 ) )
                    {
                    // InternalMORA.g:606:2: ( ( rule__Options__CppOptionsAssignment_2_2 ) )
                    // InternalMORA.g:607:3: ( rule__Options__CppOptionsAssignment_2_2 )
                    {
                     before(grammarAccess.getOptionsAccess().getCppOptionsAssignment_2_2()); 
                    // InternalMORA.g:608:3: ( rule__Options__CppOptionsAssignment_2_2 )
                    // InternalMORA.g:608:4: rule__Options__CppOptionsAssignment_2_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Options__CppOptionsAssignment_2_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getOptionsAccess().getCppOptionsAssignment_2_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Alternatives_2"


    // $ANTLR start "rule__TypeDecl__Alternatives"
    // InternalMORA.g:616:1: rule__TypeDecl__Alternatives : ( ( ruleSingleTypeDecl ) | ( ruleListTypeDecl ) );
    public final void rule__TypeDecl__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:620:1: ( ( ruleSingleTypeDecl ) | ( ruleListTypeDecl ) )
            int alt3=2;
            switch ( input.LA(1) ) {
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 34:
            case 35:
            case 36:
                {
                alt3=1;
                }
                break;
            case RULE_ML_COMMENT:
                {
                int LA3_2 = input.LA(2);

                if ( ((LA3_2>=34 && LA3_2<=36)) ) {
                    alt3=1;
                }
                else if ( (LA3_2==37) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;
                }
                }
                break;
            case 37:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalMORA.g:621:2: ( ruleSingleTypeDecl )
                    {
                    // InternalMORA.g:621:2: ( ruleSingleTypeDecl )
                    // InternalMORA.g:622:3: ruleSingleTypeDecl
                    {
                     before(grammarAccess.getTypeDeclAccess().getSingleTypeDeclParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleSingleTypeDecl();

                    state._fsp--;

                     after(grammarAccess.getTypeDeclAccess().getSingleTypeDeclParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:627:2: ( ruleListTypeDecl )
                    {
                    // InternalMORA.g:627:2: ( ruleListTypeDecl )
                    // InternalMORA.g:628:3: ruleListTypeDecl
                    {
                     before(grammarAccess.getTypeDeclAccess().getListTypeDeclParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleListTypeDecl();

                    state._fsp--;

                     after(grammarAccess.getTypeDeclAccess().getListTypeDeclParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TypeDecl__Alternatives"


    // $ANTLR start "rule__SingleTypeDecl__Alternatives"
    // InternalMORA.g:637:1: rule__SingleTypeDecl__Alternatives : ( ( rulePrimTypeDecl ) | ( ruleStructDecl ) | ( ruleEnumDecl ) );
    public final void rule__SingleTypeDecl__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:641:1: ( ( rulePrimTypeDecl ) | ( ruleStructDecl ) | ( ruleEnumDecl ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                {
                alt4=1;
                }
                break;
            case RULE_ML_COMMENT:
                {
                int LA4_2 = input.LA(2);

                if ( ((LA4_2>=34 && LA4_2<=35)) ) {
                    alt4=2;
                }
                else if ( (LA4_2==36) ) {
                    alt4=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
                }
                break;
            case 34:
            case 35:
                {
                alt4=2;
                }
                break;
            case 36:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalMORA.g:642:2: ( rulePrimTypeDecl )
                    {
                    // InternalMORA.g:642:2: ( rulePrimTypeDecl )
                    // InternalMORA.g:643:3: rulePrimTypeDecl
                    {
                     before(grammarAccess.getSingleTypeDeclAccess().getPrimTypeDeclParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    rulePrimTypeDecl();

                    state._fsp--;

                     after(grammarAccess.getSingleTypeDeclAccess().getPrimTypeDeclParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:648:2: ( ruleStructDecl )
                    {
                    // InternalMORA.g:648:2: ( ruleStructDecl )
                    // InternalMORA.g:649:3: ruleStructDecl
                    {
                     before(grammarAccess.getSingleTypeDeclAccess().getStructDeclParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleStructDecl();

                    state._fsp--;

                     after(grammarAccess.getSingleTypeDeclAccess().getStructDeclParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:654:2: ( ruleEnumDecl )
                    {
                    // InternalMORA.g:654:2: ( ruleEnumDecl )
                    // InternalMORA.g:655:3: ruleEnumDecl
                    {
                     before(grammarAccess.getSingleTypeDeclAccess().getEnumDeclParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleEnumDecl();

                    state._fsp--;

                     after(grammarAccess.getSingleTypeDeclAccess().getEnumDeclParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__SingleTypeDecl__Alternatives"


    // $ANTLR start "rule__Member__Alternatives_2"
    // InternalMORA.g:664:1: rule__Member__Alternatives_2 : ( ( ( rule__Member__ComplexTypeAssignment_2_0 ) ) | ( ( rule__Member__PrimTypeAssignment_2_1 ) ) );
    public final void rule__Member__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:668:1: ( ( ( rule__Member__ComplexTypeAssignment_2_0 ) ) | ( ( rule__Member__PrimTypeAssignment_2_1 ) ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            else if ( ((LA5_0>=13 && LA5_0<=21)) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalMORA.g:669:2: ( ( rule__Member__ComplexTypeAssignment_2_0 ) )
                    {
                    // InternalMORA.g:669:2: ( ( rule__Member__ComplexTypeAssignment_2_0 ) )
                    // InternalMORA.g:670:3: ( rule__Member__ComplexTypeAssignment_2_0 )
                    {
                     before(grammarAccess.getMemberAccess().getComplexTypeAssignment_2_0()); 
                    // InternalMORA.g:671:3: ( rule__Member__ComplexTypeAssignment_2_0 )
                    // InternalMORA.g:671:4: rule__Member__ComplexTypeAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Member__ComplexTypeAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMemberAccess().getComplexTypeAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:675:2: ( ( rule__Member__PrimTypeAssignment_2_1 ) )
                    {
                    // InternalMORA.g:675:2: ( ( rule__Member__PrimTypeAssignment_2_1 ) )
                    // InternalMORA.g:676:3: ( rule__Member__PrimTypeAssignment_2_1 )
                    {
                     before(grammarAccess.getMemberAccess().getPrimTypeAssignment_2_1()); 
                    // InternalMORA.g:677:3: ( rule__Member__PrimTypeAssignment_2_1 )
                    // InternalMORA.g:677:4: rule__Member__PrimTypeAssignment_2_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Member__PrimTypeAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getMemberAccess().getPrimTypeAssignment_2_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Alternatives_2"


    // $ANTLR start "rule__ListTypeDecl__Alternatives_3"
    // InternalMORA.g:685:1: rule__ListTypeDecl__Alternatives_3 : ( ( ( rule__ListTypeDecl__ValueTypeAssignment_3_0 ) ) | ( ( rule__ListTypeDecl__PrimTypeAssignment_3_1 ) ) );
    public final void rule__ListTypeDecl__Alternatives_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:689:1: ( ( ( rule__ListTypeDecl__ValueTypeAssignment_3_0 ) ) | ( ( rule__ListTypeDecl__PrimTypeAssignment_3_1 ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            else if ( ((LA6_0>=13 && LA6_0<=21)) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMORA.g:690:2: ( ( rule__ListTypeDecl__ValueTypeAssignment_3_0 ) )
                    {
                    // InternalMORA.g:690:2: ( ( rule__ListTypeDecl__ValueTypeAssignment_3_0 ) )
                    // InternalMORA.g:691:3: ( rule__ListTypeDecl__ValueTypeAssignment_3_0 )
                    {
                     before(grammarAccess.getListTypeDeclAccess().getValueTypeAssignment_3_0()); 
                    // InternalMORA.g:692:3: ( rule__ListTypeDecl__ValueTypeAssignment_3_0 )
                    // InternalMORA.g:692:4: rule__ListTypeDecl__ValueTypeAssignment_3_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ListTypeDecl__ValueTypeAssignment_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getListTypeDeclAccess().getValueTypeAssignment_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:696:2: ( ( rule__ListTypeDecl__PrimTypeAssignment_3_1 ) )
                    {
                    // InternalMORA.g:696:2: ( ( rule__ListTypeDecl__PrimTypeAssignment_3_1 ) )
                    // InternalMORA.g:697:3: ( rule__ListTypeDecl__PrimTypeAssignment_3_1 )
                    {
                     before(grammarAccess.getListTypeDeclAccess().getPrimTypeAssignment_3_1()); 
                    // InternalMORA.g:698:3: ( rule__ListTypeDecl__PrimTypeAssignment_3_1 )
                    // InternalMORA.g:698:4: rule__ListTypeDecl__PrimTypeAssignment_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ListTypeDecl__PrimTypeAssignment_3_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getListTypeDeclAccess().getPrimTypeAssignment_3_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Alternatives_3"


    // $ANTLR start "rule__Method__Alternatives_1"
    // InternalMORA.g:706:1: rule__Method__Alternatives_1 : ( ( ( rule__Method__Group_1_0__0 ) ) | ( ( rule__Method__Alternatives_1_1 ) ) );
    public final void rule__Method__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:710:1: ( ( ( rule__Method__Group_1_0__0 ) ) | ( ( rule__Method__Alternatives_1_1 ) ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==RULE_ID||(LA7_1>=11 && LA7_1<=12)) ) {
                    alt7=2;
                }
                else if ( (LA7_1==45) ) {
                    alt7=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA7_0>=13 && LA7_0<=21)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalMORA.g:711:2: ( ( rule__Method__Group_1_0__0 ) )
                    {
                    // InternalMORA.g:711:2: ( ( rule__Method__Group_1_0__0 ) )
                    // InternalMORA.g:712:3: ( rule__Method__Group_1_0__0 )
                    {
                     before(grammarAccess.getMethodAccess().getGroup_1_0()); 
                    // InternalMORA.g:713:3: ( rule__Method__Group_1_0__0 )
                    // InternalMORA.g:713:4: rule__Method__Group_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__Group_1_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMethodAccess().getGroup_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:717:2: ( ( rule__Method__Alternatives_1_1 ) )
                    {
                    // InternalMORA.g:717:2: ( ( rule__Method__Alternatives_1_1 ) )
                    // InternalMORA.g:718:3: ( rule__Method__Alternatives_1_1 )
                    {
                     before(grammarAccess.getMethodAccess().getAlternatives_1_1()); 
                    // InternalMORA.g:719:3: ( rule__Method__Alternatives_1_1 )
                    // InternalMORA.g:719:4: rule__Method__Alternatives_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__Alternatives_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getMethodAccess().getAlternatives_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Alternatives_1"


    // $ANTLR start "rule__Method__Alternatives_1_1"
    // InternalMORA.g:727:1: rule__Method__Alternatives_1_1 : ( ( ( rule__Method__ComplexTypeAssignment_1_1_0 ) ) | ( ( rule__Method__PrimTypeAssignment_1_1_1 ) ) );
    public final void rule__Method__Alternatives_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:731:1: ( ( ( rule__Method__ComplexTypeAssignment_1_1_0 ) ) | ( ( rule__Method__PrimTypeAssignment_1_1_1 ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            else if ( ((LA8_0>=13 && LA8_0<=21)) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalMORA.g:732:2: ( ( rule__Method__ComplexTypeAssignment_1_1_0 ) )
                    {
                    // InternalMORA.g:732:2: ( ( rule__Method__ComplexTypeAssignment_1_1_0 ) )
                    // InternalMORA.g:733:3: ( rule__Method__ComplexTypeAssignment_1_1_0 )
                    {
                     before(grammarAccess.getMethodAccess().getComplexTypeAssignment_1_1_0()); 
                    // InternalMORA.g:734:3: ( rule__Method__ComplexTypeAssignment_1_1_0 )
                    // InternalMORA.g:734:4: rule__Method__ComplexTypeAssignment_1_1_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__ComplexTypeAssignment_1_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMethodAccess().getComplexTypeAssignment_1_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:738:2: ( ( rule__Method__PrimTypeAssignment_1_1_1 ) )
                    {
                    // InternalMORA.g:738:2: ( ( rule__Method__PrimTypeAssignment_1_1_1 ) )
                    // InternalMORA.g:739:3: ( rule__Method__PrimTypeAssignment_1_1_1 )
                    {
                     before(grammarAccess.getMethodAccess().getPrimTypeAssignment_1_1_1()); 
                    // InternalMORA.g:740:3: ( rule__Method__PrimTypeAssignment_1_1_1 )
                    // InternalMORA.g:740:4: rule__Method__PrimTypeAssignment_1_1_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__PrimTypeAssignment_1_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getMethodAccess().getPrimTypeAssignment_1_1_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Alternatives_1_1"


    // $ANTLR start "rule__Parameter__Alternatives_0"
    // InternalMORA.g:748:1: rule__Parameter__Alternatives_0 : ( ( ( rule__Parameter__ComplexTypeAssignment_0_0 ) ) | ( ( rule__Parameter__PrimTypeAssignment_0_1 ) ) | ( ( rule__Parameter__Group_0_2__0 ) ) );
    public final void rule__Parameter__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:752:1: ( ( ( rule__Parameter__ComplexTypeAssignment_0_0 ) ) | ( ( rule__Parameter__PrimTypeAssignment_0_1 ) ) | ( ( rule__Parameter__Group_0_2__0 ) ) )
            int alt9=3;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==45) ) {
                    alt9=3;
                }
                else if ( (LA9_1==RULE_ID||(LA9_1>=11 && LA9_1<=12)) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA9_0>=13 && LA9_0<=21)) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalMORA.g:753:2: ( ( rule__Parameter__ComplexTypeAssignment_0_0 ) )
                    {
                    // InternalMORA.g:753:2: ( ( rule__Parameter__ComplexTypeAssignment_0_0 ) )
                    // InternalMORA.g:754:3: ( rule__Parameter__ComplexTypeAssignment_0_0 )
                    {
                     before(grammarAccess.getParameterAccess().getComplexTypeAssignment_0_0()); 
                    // InternalMORA.g:755:3: ( rule__Parameter__ComplexTypeAssignment_0_0 )
                    // InternalMORA.g:755:4: rule__Parameter__ComplexTypeAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__ComplexTypeAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getComplexTypeAssignment_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:759:2: ( ( rule__Parameter__PrimTypeAssignment_0_1 ) )
                    {
                    // InternalMORA.g:759:2: ( ( rule__Parameter__PrimTypeAssignment_0_1 ) )
                    // InternalMORA.g:760:3: ( rule__Parameter__PrimTypeAssignment_0_1 )
                    {
                     before(grammarAccess.getParameterAccess().getPrimTypeAssignment_0_1()); 
                    // InternalMORA.g:761:3: ( rule__Parameter__PrimTypeAssignment_0_1 )
                    // InternalMORA.g:761:4: rule__Parameter__PrimTypeAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__PrimTypeAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getPrimTypeAssignment_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:765:2: ( ( rule__Parameter__Group_0_2__0 ) )
                    {
                    // InternalMORA.g:765:2: ( ( rule__Parameter__Group_0_2__0 ) )
                    // InternalMORA.g:766:3: ( rule__Parameter__Group_0_2__0 )
                    {
                     before(grammarAccess.getParameterAccess().getGroup_0_2()); 
                    // InternalMORA.g:767:3: ( rule__Parameter__Group_0_2__0 )
                    // InternalMORA.g:767:4: rule__Parameter__Group_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Parameter__Group_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getParameterAccess().getGroup_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Alternatives_0"


    // $ANTLR start "rule__QualifiedName__Alternatives_1_0"
    // InternalMORA.g:775:1: rule__QualifiedName__Alternatives_1_0 : ( ( '.' ) | ( '::' ) );
    public final void rule__QualifiedName__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:779:1: ( ( '.' ) | ( '::' ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==11) ) {
                alt10=1;
            }
            else if ( (LA10_0==12) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalMORA.g:780:2: ( '.' )
                    {
                    // InternalMORA.g:780:2: ( '.' )
                    // InternalMORA.g:781:3: '.'
                    {
                     before(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:786:2: ( '::' )
                    {
                    // InternalMORA.g:786:2: ( '::' )
                    // InternalMORA.g:787:3: '::'
                    {
                     before(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1_0_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Alternatives_1_0"


    // $ANTLR start "rule__PrimTypeLiteral__Alternatives"
    // InternalMORA.g:796:1: rule__PrimTypeLiteral__Alternatives : ( ( ( 'bool' ) ) | ( ( 'byte' ) ) | ( ( 'short' ) ) | ( ( 'int' ) ) | ( ( 'long' ) ) | ( ( 'float' ) ) | ( ( 'double' ) ) | ( ( 'string' ) ) | ( ( 'void' ) ) );
    public final void rule__PrimTypeLiteral__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:800:1: ( ( ( 'bool' ) ) | ( ( 'byte' ) ) | ( ( 'short' ) ) | ( ( 'int' ) ) | ( ( 'long' ) ) | ( ( 'float' ) ) | ( ( 'double' ) ) | ( ( 'string' ) ) | ( ( 'void' ) ) )
            int alt11=9;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt11=1;
                }
                break;
            case 14:
                {
                alt11=2;
                }
                break;
            case 15:
                {
                alt11=3;
                }
                break;
            case 16:
                {
                alt11=4;
                }
                break;
            case 17:
                {
                alt11=5;
                }
                break;
            case 18:
                {
                alt11=6;
                }
                break;
            case 19:
                {
                alt11=7;
                }
                break;
            case 20:
                {
                alt11=8;
                }
                break;
            case 21:
                {
                alt11=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalMORA.g:801:2: ( ( 'bool' ) )
                    {
                    // InternalMORA.g:801:2: ( ( 'bool' ) )
                    // InternalMORA.g:802:3: ( 'bool' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getBOOLEnumLiteralDeclaration_0()); 
                    // InternalMORA.g:803:3: ( 'bool' )
                    // InternalMORA.g:803:4: 'bool'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getBOOLEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:807:2: ( ( 'byte' ) )
                    {
                    // InternalMORA.g:807:2: ( ( 'byte' ) )
                    // InternalMORA.g:808:3: ( 'byte' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getBYTEEnumLiteralDeclaration_1()); 
                    // InternalMORA.g:809:3: ( 'byte' )
                    // InternalMORA.g:809:4: 'byte'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getBYTEEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:813:2: ( ( 'short' ) )
                    {
                    // InternalMORA.g:813:2: ( ( 'short' ) )
                    // InternalMORA.g:814:3: ( 'short' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getSHORTEnumLiteralDeclaration_2()); 
                    // InternalMORA.g:815:3: ( 'short' )
                    // InternalMORA.g:815:4: 'short'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getSHORTEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMORA.g:819:2: ( ( 'int' ) )
                    {
                    // InternalMORA.g:819:2: ( ( 'int' ) )
                    // InternalMORA.g:820:3: ( 'int' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getINTEGEREnumLiteralDeclaration_3()); 
                    // InternalMORA.g:821:3: ( 'int' )
                    // InternalMORA.g:821:4: 'int'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getINTEGEREnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMORA.g:825:2: ( ( 'long' ) )
                    {
                    // InternalMORA.g:825:2: ( ( 'long' ) )
                    // InternalMORA.g:826:3: ( 'long' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getLONGEnumLiteralDeclaration_4()); 
                    // InternalMORA.g:827:3: ( 'long' )
                    // InternalMORA.g:827:4: 'long'
                    {
                    match(input,17,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getLONGEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalMORA.g:831:2: ( ( 'float' ) )
                    {
                    // InternalMORA.g:831:2: ( ( 'float' ) )
                    // InternalMORA.g:832:3: ( 'float' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getFLOATEnumLiteralDeclaration_5()); 
                    // InternalMORA.g:833:3: ( 'float' )
                    // InternalMORA.g:833:4: 'float'
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getFLOATEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalMORA.g:837:2: ( ( 'double' ) )
                    {
                    // InternalMORA.g:837:2: ( ( 'double' ) )
                    // InternalMORA.g:838:3: ( 'double' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getDOUBLEEnumLiteralDeclaration_6()); 
                    // InternalMORA.g:839:3: ( 'double' )
                    // InternalMORA.g:839:4: 'double'
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getDOUBLEEnumLiteralDeclaration_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalMORA.g:843:2: ( ( 'string' ) )
                    {
                    // InternalMORA.g:843:2: ( ( 'string' ) )
                    // InternalMORA.g:844:3: ( 'string' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getSTRINGEnumLiteralDeclaration_7()); 
                    // InternalMORA.g:845:3: ( 'string' )
                    // InternalMORA.g:845:4: 'string'
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getSTRINGEnumLiteralDeclaration_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalMORA.g:849:2: ( ( 'void' ) )
                    {
                    // InternalMORA.g:849:2: ( ( 'void' ) )
                    // InternalMORA.g:850:3: ( 'void' )
                    {
                     before(grammarAccess.getPrimTypeLiteralAccess().getVOIDEnumLiteralDeclaration_8()); 
                    // InternalMORA.g:851:3: ( 'void' )
                    // InternalMORA.g:851:4: 'void'
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getPrimTypeLiteralAccess().getVOIDEnumLiteralDeclaration_8()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimTypeLiteral__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // InternalMORA.g:859:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:863:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalMORA.g:864:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // InternalMORA.g:871:1: rule__Model__Group__0__Impl : ( ( rule__Model__IncludesAssignment_0 )* ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:875:1: ( ( ( rule__Model__IncludesAssignment_0 )* ) )
            // InternalMORA.g:876:1: ( ( rule__Model__IncludesAssignment_0 )* )
            {
            // InternalMORA.g:876:1: ( ( rule__Model__IncludesAssignment_0 )* )
            // InternalMORA.g:877:2: ( rule__Model__IncludesAssignment_0 )*
            {
             before(grammarAccess.getModelAccess().getIncludesAssignment_0()); 
            // InternalMORA.g:878:2: ( rule__Model__IncludesAssignment_0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==26) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMORA.g:878:3: rule__Model__IncludesAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Model__IncludesAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getIncludesAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // InternalMORA.g:886:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:890:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // InternalMORA.g:891:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // InternalMORA.g:898:1: rule__Model__Group__1__Impl : ( ( rule__Model__OptionsAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:902:1: ( ( ( rule__Model__OptionsAssignment_1 )? ) )
            // InternalMORA.g:903:1: ( ( rule__Model__OptionsAssignment_1 )? )
            {
            // InternalMORA.g:903:1: ( ( rule__Model__OptionsAssignment_1 )? )
            // InternalMORA.g:904:2: ( rule__Model__OptionsAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getOptionsAssignment_1()); 
            // InternalMORA.g:905:2: ( rule__Model__OptionsAssignment_1 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==27) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalMORA.g:905:3: rule__Model__OptionsAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__OptionsAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getOptionsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Model__Group__2"
    // InternalMORA.g:913:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:917:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // InternalMORA.g:918:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Model__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2"


    // $ANTLR start "rule__Model__Group__2__Impl"
    // InternalMORA.g:925:1: rule__Model__Group__2__Impl : ( 'package' ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:929:1: ( ( 'package' ) )
            // InternalMORA.g:930:1: ( 'package' )
            {
            // InternalMORA.g:930:1: ( 'package' )
            // InternalMORA.g:931:2: 'package'
            {
             before(grammarAccess.getModelAccess().getPackageKeyword_2()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getPackageKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2__Impl"


    // $ANTLR start "rule__Model__Group__3"
    // InternalMORA.g:940:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:944:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // InternalMORA.g:945:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Model__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3"


    // $ANTLR start "rule__Model__Group__3__Impl"
    // InternalMORA.g:952:1: rule__Model__Group__3__Impl : ( ( rule__Model__NameAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:956:1: ( ( ( rule__Model__NameAssignment_3 ) ) )
            // InternalMORA.g:957:1: ( ( rule__Model__NameAssignment_3 ) )
            {
            // InternalMORA.g:957:1: ( ( rule__Model__NameAssignment_3 ) )
            // InternalMORA.g:958:2: ( rule__Model__NameAssignment_3 )
            {
             before(grammarAccess.getModelAccess().getNameAssignment_3()); 
            // InternalMORA.g:959:2: ( rule__Model__NameAssignment_3 )
            // InternalMORA.g:959:3: rule__Model__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Model__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3__Impl"


    // $ANTLR start "rule__Model__Group__4"
    // InternalMORA.g:967:1: rule__Model__Group__4 : rule__Model__Group__4__Impl rule__Model__Group__5 ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:971:1: ( rule__Model__Group__4__Impl rule__Model__Group__5 )
            // InternalMORA.g:972:2: rule__Model__Group__4__Impl rule__Model__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__Model__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4"


    // $ANTLR start "rule__Model__Group__4__Impl"
    // InternalMORA.g:979:1: rule__Model__Group__4__Impl : ( '{' ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:983:1: ( ( '{' ) )
            // InternalMORA.g:984:1: ( '{' )
            {
            // InternalMORA.g:984:1: ( '{' )
            // InternalMORA.g:985:2: '{'
            {
             before(grammarAccess.getModelAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getLeftCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4__Impl"


    // $ANTLR start "rule__Model__Group__5"
    // InternalMORA.g:994:1: rule__Model__Group__5 : rule__Model__Group__5__Impl rule__Model__Group__6 ;
    public final void rule__Model__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:998:1: ( rule__Model__Group__5__Impl rule__Model__Group__6 )
            // InternalMORA.g:999:2: rule__Model__Group__5__Impl rule__Model__Group__6
            {
            pushFollow(FOLLOW_7);
            rule__Model__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__5"


    // $ANTLR start "rule__Model__Group__5__Impl"
    // InternalMORA.g:1006:1: rule__Model__Group__5__Impl : ( ( rule__Model__Group_5__0 )* ) ;
    public final void rule__Model__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1010:1: ( ( ( rule__Model__Group_5__0 )* ) )
            // InternalMORA.g:1011:1: ( ( rule__Model__Group_5__0 )* )
            {
            // InternalMORA.g:1011:1: ( ( rule__Model__Group_5__0 )* )
            // InternalMORA.g:1012:2: ( rule__Model__Group_5__0 )*
            {
             before(grammarAccess.getModelAccess().getGroup_5()); 
            // InternalMORA.g:1013:2: ( rule__Model__Group_5__0 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ML_COMMENT||(LA14_0>=13 && LA14_0<=21)||(LA14_0>=34 && LA14_0<=37)||LA14_0==40) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalMORA.g:1013:3: rule__Model__Group_5__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Model__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__5__Impl"


    // $ANTLR start "rule__Model__Group__6"
    // InternalMORA.g:1021:1: rule__Model__Group__6 : rule__Model__Group__6__Impl ;
    public final void rule__Model__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1025:1: ( rule__Model__Group__6__Impl )
            // InternalMORA.g:1026:2: rule__Model__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__6"


    // $ANTLR start "rule__Model__Group__6__Impl"
    // InternalMORA.g:1032:1: rule__Model__Group__6__Impl : ( '}' ) ;
    public final void rule__Model__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1036:1: ( ( '}' ) )
            // InternalMORA.g:1037:1: ( '}' )
            {
            // InternalMORA.g:1037:1: ( '}' )
            // InternalMORA.g:1038:2: '}'
            {
             before(grammarAccess.getModelAccess().getRightCurlyBracketKeyword_6()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__6__Impl"


    // $ANTLR start "rule__Model__Group_5__0"
    // InternalMORA.g:1048:1: rule__Model__Group_5__0 : rule__Model__Group_5__0__Impl rule__Model__Group_5__1 ;
    public final void rule__Model__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1052:1: ( rule__Model__Group_5__0__Impl rule__Model__Group_5__1 )
            // InternalMORA.g:1053:2: rule__Model__Group_5__0__Impl rule__Model__Group_5__1
            {
            pushFollow(FOLLOW_9);
            rule__Model__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_5__0"


    // $ANTLR start "rule__Model__Group_5__0__Impl"
    // InternalMORA.g:1060:1: rule__Model__Group_5__0__Impl : ( ( rule__Model__Alternatives_5_0 ) ) ;
    public final void rule__Model__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1064:1: ( ( ( rule__Model__Alternatives_5_0 ) ) )
            // InternalMORA.g:1065:1: ( ( rule__Model__Alternatives_5_0 ) )
            {
            // InternalMORA.g:1065:1: ( ( rule__Model__Alternatives_5_0 ) )
            // InternalMORA.g:1066:2: ( rule__Model__Alternatives_5_0 )
            {
             before(grammarAccess.getModelAccess().getAlternatives_5_0()); 
            // InternalMORA.g:1067:2: ( rule__Model__Alternatives_5_0 )
            // InternalMORA.g:1067:3: rule__Model__Alternatives_5_0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Alternatives_5_0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getAlternatives_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_5__0__Impl"


    // $ANTLR start "rule__Model__Group_5__1"
    // InternalMORA.g:1075:1: rule__Model__Group_5__1 : rule__Model__Group_5__1__Impl ;
    public final void rule__Model__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1079:1: ( rule__Model__Group_5__1__Impl )
            // InternalMORA.g:1080:2: rule__Model__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_5__1"


    // $ANTLR start "rule__Model__Group_5__1__Impl"
    // InternalMORA.g:1086:1: rule__Model__Group_5__1__Impl : ( ( ';' )? ) ;
    public final void rule__Model__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1090:1: ( ( ( ';' )? ) )
            // InternalMORA.g:1091:1: ( ( ';' )? )
            {
            // InternalMORA.g:1091:1: ( ( ';' )? )
            // InternalMORA.g:1092:2: ( ';' )?
            {
             before(grammarAccess.getModelAccess().getSemicolonKeyword_5_1()); 
            // InternalMORA.g:1093:2: ( ';' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==25) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMORA.g:1093:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getSemicolonKeyword_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group_5__1__Impl"


    // $ANTLR start "rule__Include__Group__0"
    // InternalMORA.g:1102:1: rule__Include__Group__0 : rule__Include__Group__0__Impl rule__Include__Group__1 ;
    public final void rule__Include__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1106:1: ( rule__Include__Group__0__Impl rule__Include__Group__1 )
            // InternalMORA.g:1107:2: rule__Include__Group__0__Impl rule__Include__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Include__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Include__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__0"


    // $ANTLR start "rule__Include__Group__0__Impl"
    // InternalMORA.g:1114:1: rule__Include__Group__0__Impl : ( 'import' ) ;
    public final void rule__Include__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1118:1: ( ( 'import' ) )
            // InternalMORA.g:1119:1: ( 'import' )
            {
            // InternalMORA.g:1119:1: ( 'import' )
            // InternalMORA.g:1120:2: 'import'
            {
             before(grammarAccess.getIncludeAccess().getImportKeyword_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getIncludeAccess().getImportKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__0__Impl"


    // $ANTLR start "rule__Include__Group__1"
    // InternalMORA.g:1129:1: rule__Include__Group__1 : rule__Include__Group__1__Impl rule__Include__Group__2 ;
    public final void rule__Include__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1133:1: ( rule__Include__Group__1__Impl rule__Include__Group__2 )
            // InternalMORA.g:1134:2: rule__Include__Group__1__Impl rule__Include__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Include__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Include__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__1"


    // $ANTLR start "rule__Include__Group__1__Impl"
    // InternalMORA.g:1141:1: rule__Include__Group__1__Impl : ( ( rule__Include__ImportUriAssignment_1 ) ) ;
    public final void rule__Include__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1145:1: ( ( ( rule__Include__ImportUriAssignment_1 ) ) )
            // InternalMORA.g:1146:1: ( ( rule__Include__ImportUriAssignment_1 ) )
            {
            // InternalMORA.g:1146:1: ( ( rule__Include__ImportUriAssignment_1 ) )
            // InternalMORA.g:1147:2: ( rule__Include__ImportUriAssignment_1 )
            {
             before(grammarAccess.getIncludeAccess().getImportUriAssignment_1()); 
            // InternalMORA.g:1148:2: ( rule__Include__ImportUriAssignment_1 )
            // InternalMORA.g:1148:3: rule__Include__ImportUriAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Include__ImportUriAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getIncludeAccess().getImportUriAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__1__Impl"


    // $ANTLR start "rule__Include__Group__2"
    // InternalMORA.g:1156:1: rule__Include__Group__2 : rule__Include__Group__2__Impl ;
    public final void rule__Include__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1160:1: ( rule__Include__Group__2__Impl )
            // InternalMORA.g:1161:2: rule__Include__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Include__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__2"


    // $ANTLR start "rule__Include__Group__2__Impl"
    // InternalMORA.g:1167:1: rule__Include__Group__2__Impl : ( ( ';' )? ) ;
    public final void rule__Include__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1171:1: ( ( ( ';' )? ) )
            // InternalMORA.g:1172:1: ( ( ';' )? )
            {
            // InternalMORA.g:1172:1: ( ( ';' )? )
            // InternalMORA.g:1173:2: ( ';' )?
            {
             before(grammarAccess.getIncludeAccess().getSemicolonKeyword_2()); 
            // InternalMORA.g:1174:2: ( ';' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==25) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalMORA.g:1174:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getIncludeAccess().getSemicolonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__Group__2__Impl"


    // $ANTLR start "rule__Options__Group__0"
    // InternalMORA.g:1183:1: rule__Options__Group__0 : rule__Options__Group__0__Impl rule__Options__Group__1 ;
    public final void rule__Options__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1187:1: ( rule__Options__Group__0__Impl rule__Options__Group__1 )
            // InternalMORA.g:1188:2: rule__Options__Group__0__Impl rule__Options__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Options__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Options__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__0"


    // $ANTLR start "rule__Options__Group__0__Impl"
    // InternalMORA.g:1195:1: rule__Options__Group__0__Impl : ( 'options' ) ;
    public final void rule__Options__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1199:1: ( ( 'options' ) )
            // InternalMORA.g:1200:1: ( 'options' )
            {
            // InternalMORA.g:1200:1: ( 'options' )
            // InternalMORA.g:1201:2: 'options'
            {
             before(grammarAccess.getOptionsAccess().getOptionsKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getOptionsAccess().getOptionsKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__0__Impl"


    // $ANTLR start "rule__Options__Group__1"
    // InternalMORA.g:1210:1: rule__Options__Group__1 : rule__Options__Group__1__Impl rule__Options__Group__2 ;
    public final void rule__Options__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1214:1: ( rule__Options__Group__1__Impl rule__Options__Group__2 )
            // InternalMORA.g:1215:2: rule__Options__Group__1__Impl rule__Options__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Options__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Options__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__1"


    // $ANTLR start "rule__Options__Group__1__Impl"
    // InternalMORA.g:1222:1: rule__Options__Group__1__Impl : ( '{' ) ;
    public final void rule__Options__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1226:1: ( ( '{' ) )
            // InternalMORA.g:1227:1: ( '{' )
            {
            // InternalMORA.g:1227:1: ( '{' )
            // InternalMORA.g:1228:2: '{'
            {
             before(grammarAccess.getOptionsAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getOptionsAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__1__Impl"


    // $ANTLR start "rule__Options__Group__2"
    // InternalMORA.g:1237:1: rule__Options__Group__2 : rule__Options__Group__2__Impl rule__Options__Group__3 ;
    public final void rule__Options__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1241:1: ( rule__Options__Group__2__Impl rule__Options__Group__3 )
            // InternalMORA.g:1242:2: rule__Options__Group__2__Impl rule__Options__Group__3
            {
            pushFollow(FOLLOW_12);
            rule__Options__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Options__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__2"


    // $ANTLR start "rule__Options__Group__2__Impl"
    // InternalMORA.g:1249:1: rule__Options__Group__2__Impl : ( ( ( rule__Options__Alternatives_2 ) ) ( ( rule__Options__Alternatives_2 )* ) ) ;
    public final void rule__Options__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1253:1: ( ( ( ( rule__Options__Alternatives_2 ) ) ( ( rule__Options__Alternatives_2 )* ) ) )
            // InternalMORA.g:1254:1: ( ( ( rule__Options__Alternatives_2 ) ) ( ( rule__Options__Alternatives_2 )* ) )
            {
            // InternalMORA.g:1254:1: ( ( ( rule__Options__Alternatives_2 ) ) ( ( rule__Options__Alternatives_2 )* ) )
            // InternalMORA.g:1255:2: ( ( rule__Options__Alternatives_2 ) ) ( ( rule__Options__Alternatives_2 )* )
            {
            // InternalMORA.g:1255:2: ( ( rule__Options__Alternatives_2 ) )
            // InternalMORA.g:1256:3: ( rule__Options__Alternatives_2 )
            {
             before(grammarAccess.getOptionsAccess().getAlternatives_2()); 
            // InternalMORA.g:1257:3: ( rule__Options__Alternatives_2 )
            // InternalMORA.g:1257:4: rule__Options__Alternatives_2
            {
            pushFollow(FOLLOW_13);
            rule__Options__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getOptionsAccess().getAlternatives_2()); 

            }

            // InternalMORA.g:1260:2: ( ( rule__Options__Alternatives_2 )* )
            // InternalMORA.g:1261:3: ( rule__Options__Alternatives_2 )*
            {
             before(grammarAccess.getOptionsAccess().getAlternatives_2()); 
            // InternalMORA.g:1262:3: ( rule__Options__Alternatives_2 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==28||LA17_0==31||LA17_0==33) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalMORA.g:1262:4: rule__Options__Alternatives_2
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__Options__Alternatives_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getOptionsAccess().getAlternatives_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__2__Impl"


    // $ANTLR start "rule__Options__Group__3"
    // InternalMORA.g:1271:1: rule__Options__Group__3 : rule__Options__Group__3__Impl ;
    public final void rule__Options__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1275:1: ( rule__Options__Group__3__Impl )
            // InternalMORA.g:1276:2: rule__Options__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Options__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__3"


    // $ANTLR start "rule__Options__Group__3__Impl"
    // InternalMORA.g:1282:1: rule__Options__Group__3__Impl : ( '}' ) ;
    public final void rule__Options__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1286:1: ( ( '}' ) )
            // InternalMORA.g:1287:1: ( '}' )
            {
            // InternalMORA.g:1287:1: ( '}' )
            // InternalMORA.g:1288:2: '}'
            {
             before(grammarAccess.getOptionsAccess().getRightCurlyBracketKeyword_3()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getOptionsAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__Group__3__Impl"


    // $ANTLR start "rule__JavaOptions__Group__0"
    // InternalMORA.g:1298:1: rule__JavaOptions__Group__0 : rule__JavaOptions__Group__0__Impl rule__JavaOptions__Group__1 ;
    public final void rule__JavaOptions__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1302:1: ( rule__JavaOptions__Group__0__Impl rule__JavaOptions__Group__1 )
            // InternalMORA.g:1303:2: rule__JavaOptions__Group__0__Impl rule__JavaOptions__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__JavaOptions__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__0"


    // $ANTLR start "rule__JavaOptions__Group__0__Impl"
    // InternalMORA.g:1310:1: rule__JavaOptions__Group__0__Impl : ( 'java' ) ;
    public final void rule__JavaOptions__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1314:1: ( ( 'java' ) )
            // InternalMORA.g:1315:1: ( 'java' )
            {
            // InternalMORA.g:1315:1: ( 'java' )
            // InternalMORA.g:1316:2: 'java'
            {
             before(grammarAccess.getJavaOptionsAccess().getJavaKeyword_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getJavaOptionsAccess().getJavaKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__0__Impl"


    // $ANTLR start "rule__JavaOptions__Group__1"
    // InternalMORA.g:1325:1: rule__JavaOptions__Group__1 : rule__JavaOptions__Group__1__Impl rule__JavaOptions__Group__2 ;
    public final void rule__JavaOptions__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1329:1: ( rule__JavaOptions__Group__1__Impl rule__JavaOptions__Group__2 )
            // InternalMORA.g:1330:2: rule__JavaOptions__Group__1__Impl rule__JavaOptions__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__JavaOptions__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__1"


    // $ANTLR start "rule__JavaOptions__Group__1__Impl"
    // InternalMORA.g:1337:1: rule__JavaOptions__Group__1__Impl : ( '{' ) ;
    public final void rule__JavaOptions__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1341:1: ( ( '{' ) )
            // InternalMORA.g:1342:1: ( '{' )
            {
            // InternalMORA.g:1342:1: ( '{' )
            // InternalMORA.g:1343:2: '{'
            {
             before(grammarAccess.getJavaOptionsAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getJavaOptionsAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__1__Impl"


    // $ANTLR start "rule__JavaOptions__Group__2"
    // InternalMORA.g:1352:1: rule__JavaOptions__Group__2 : rule__JavaOptions__Group__2__Impl rule__JavaOptions__Group__3 ;
    public final void rule__JavaOptions__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1356:1: ( rule__JavaOptions__Group__2__Impl rule__JavaOptions__Group__3 )
            // InternalMORA.g:1357:2: rule__JavaOptions__Group__2__Impl rule__JavaOptions__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__JavaOptions__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__2"


    // $ANTLR start "rule__JavaOptions__Group__2__Impl"
    // InternalMORA.g:1364:1: rule__JavaOptions__Group__2__Impl : ( 'base-package' ) ;
    public final void rule__JavaOptions__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1368:1: ( ( 'base-package' ) )
            // InternalMORA.g:1369:1: ( 'base-package' )
            {
            // InternalMORA.g:1369:1: ( 'base-package' )
            // InternalMORA.g:1370:2: 'base-package'
            {
             before(grammarAccess.getJavaOptionsAccess().getBasePackageKeyword_2()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getJavaOptionsAccess().getBasePackageKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__2__Impl"


    // $ANTLR start "rule__JavaOptions__Group__3"
    // InternalMORA.g:1379:1: rule__JavaOptions__Group__3 : rule__JavaOptions__Group__3__Impl rule__JavaOptions__Group__4 ;
    public final void rule__JavaOptions__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1383:1: ( rule__JavaOptions__Group__3__Impl rule__JavaOptions__Group__4 )
            // InternalMORA.g:1384:2: rule__JavaOptions__Group__3__Impl rule__JavaOptions__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__JavaOptions__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__3"


    // $ANTLR start "rule__JavaOptions__Group__3__Impl"
    // InternalMORA.g:1391:1: rule__JavaOptions__Group__3__Impl : ( '=' ) ;
    public final void rule__JavaOptions__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1395:1: ( ( '=' ) )
            // InternalMORA.g:1396:1: ( '=' )
            {
            // InternalMORA.g:1396:1: ( '=' )
            // InternalMORA.g:1397:2: '='
            {
             before(grammarAccess.getJavaOptionsAccess().getEqualsSignKeyword_3()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getJavaOptionsAccess().getEqualsSignKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__3__Impl"


    // $ANTLR start "rule__JavaOptions__Group__4"
    // InternalMORA.g:1406:1: rule__JavaOptions__Group__4 : rule__JavaOptions__Group__4__Impl rule__JavaOptions__Group__5 ;
    public final void rule__JavaOptions__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1410:1: ( rule__JavaOptions__Group__4__Impl rule__JavaOptions__Group__5 )
            // InternalMORA.g:1411:2: rule__JavaOptions__Group__4__Impl rule__JavaOptions__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__JavaOptions__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__4"


    // $ANTLR start "rule__JavaOptions__Group__4__Impl"
    // InternalMORA.g:1418:1: rule__JavaOptions__Group__4__Impl : ( ( rule__JavaOptions__BasePackageAssignment_4 ) ) ;
    public final void rule__JavaOptions__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1422:1: ( ( ( rule__JavaOptions__BasePackageAssignment_4 ) ) )
            // InternalMORA.g:1423:1: ( ( rule__JavaOptions__BasePackageAssignment_4 ) )
            {
            // InternalMORA.g:1423:1: ( ( rule__JavaOptions__BasePackageAssignment_4 ) )
            // InternalMORA.g:1424:2: ( rule__JavaOptions__BasePackageAssignment_4 )
            {
             before(grammarAccess.getJavaOptionsAccess().getBasePackageAssignment_4()); 
            // InternalMORA.g:1425:2: ( rule__JavaOptions__BasePackageAssignment_4 )
            // InternalMORA.g:1425:3: rule__JavaOptions__BasePackageAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__JavaOptions__BasePackageAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getJavaOptionsAccess().getBasePackageAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__4__Impl"


    // $ANTLR start "rule__JavaOptions__Group__5"
    // InternalMORA.g:1433:1: rule__JavaOptions__Group__5 : rule__JavaOptions__Group__5__Impl ;
    public final void rule__JavaOptions__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1437:1: ( rule__JavaOptions__Group__5__Impl )
            // InternalMORA.g:1438:2: rule__JavaOptions__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__JavaOptions__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__5"


    // $ANTLR start "rule__JavaOptions__Group__5__Impl"
    // InternalMORA.g:1444:1: rule__JavaOptions__Group__5__Impl : ( '}' ) ;
    public final void rule__JavaOptions__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1448:1: ( ( '}' ) )
            // InternalMORA.g:1449:1: ( '}' )
            {
            // InternalMORA.g:1449:1: ( '}' )
            // InternalMORA.g:1450:2: '}'
            {
             before(grammarAccess.getJavaOptionsAccess().getRightCurlyBracketKeyword_5()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getJavaOptionsAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__Group__5__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__0"
    // InternalMORA.g:1460:1: rule__CSharpOptions__Group__0 : rule__CSharpOptions__Group__0__Impl rule__CSharpOptions__Group__1 ;
    public final void rule__CSharpOptions__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1464:1: ( rule__CSharpOptions__Group__0__Impl rule__CSharpOptions__Group__1 )
            // InternalMORA.g:1465:2: rule__CSharpOptions__Group__0__Impl rule__CSharpOptions__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__CSharpOptions__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__0"


    // $ANTLR start "rule__CSharpOptions__Group__0__Impl"
    // InternalMORA.g:1472:1: rule__CSharpOptions__Group__0__Impl : ( 'csharp' ) ;
    public final void rule__CSharpOptions__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1476:1: ( ( 'csharp' ) )
            // InternalMORA.g:1477:1: ( 'csharp' )
            {
            // InternalMORA.g:1477:1: ( 'csharp' )
            // InternalMORA.g:1478:2: 'csharp'
            {
             before(grammarAccess.getCSharpOptionsAccess().getCsharpKeyword_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getCSharpOptionsAccess().getCsharpKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__0__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__1"
    // InternalMORA.g:1487:1: rule__CSharpOptions__Group__1 : rule__CSharpOptions__Group__1__Impl rule__CSharpOptions__Group__2 ;
    public final void rule__CSharpOptions__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1491:1: ( rule__CSharpOptions__Group__1__Impl rule__CSharpOptions__Group__2 )
            // InternalMORA.g:1492:2: rule__CSharpOptions__Group__1__Impl rule__CSharpOptions__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__CSharpOptions__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__1"


    // $ANTLR start "rule__CSharpOptions__Group__1__Impl"
    // InternalMORA.g:1499:1: rule__CSharpOptions__Group__1__Impl : ( '{' ) ;
    public final void rule__CSharpOptions__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1503:1: ( ( '{' ) )
            // InternalMORA.g:1504:1: ( '{' )
            {
            // InternalMORA.g:1504:1: ( '{' )
            // InternalMORA.g:1505:2: '{'
            {
             before(grammarAccess.getCSharpOptionsAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getCSharpOptionsAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__1__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__2"
    // InternalMORA.g:1514:1: rule__CSharpOptions__Group__2 : rule__CSharpOptions__Group__2__Impl rule__CSharpOptions__Group__3 ;
    public final void rule__CSharpOptions__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1518:1: ( rule__CSharpOptions__Group__2__Impl rule__CSharpOptions__Group__3 )
            // InternalMORA.g:1519:2: rule__CSharpOptions__Group__2__Impl rule__CSharpOptions__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__CSharpOptions__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__2"


    // $ANTLR start "rule__CSharpOptions__Group__2__Impl"
    // InternalMORA.g:1526:1: rule__CSharpOptions__Group__2__Impl : ( 'base-namespace' ) ;
    public final void rule__CSharpOptions__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1530:1: ( ( 'base-namespace' ) )
            // InternalMORA.g:1531:1: ( 'base-namespace' )
            {
            // InternalMORA.g:1531:1: ( 'base-namespace' )
            // InternalMORA.g:1532:2: 'base-namespace'
            {
             before(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__2__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__3"
    // InternalMORA.g:1541:1: rule__CSharpOptions__Group__3 : rule__CSharpOptions__Group__3__Impl rule__CSharpOptions__Group__4 ;
    public final void rule__CSharpOptions__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1545:1: ( rule__CSharpOptions__Group__3__Impl rule__CSharpOptions__Group__4 )
            // InternalMORA.g:1546:2: rule__CSharpOptions__Group__3__Impl rule__CSharpOptions__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__CSharpOptions__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__3"


    // $ANTLR start "rule__CSharpOptions__Group__3__Impl"
    // InternalMORA.g:1553:1: rule__CSharpOptions__Group__3__Impl : ( '=' ) ;
    public final void rule__CSharpOptions__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1557:1: ( ( '=' ) )
            // InternalMORA.g:1558:1: ( '=' )
            {
            // InternalMORA.g:1558:1: ( '=' )
            // InternalMORA.g:1559:2: '='
            {
             before(grammarAccess.getCSharpOptionsAccess().getEqualsSignKeyword_3()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getCSharpOptionsAccess().getEqualsSignKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__3__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__4"
    // InternalMORA.g:1568:1: rule__CSharpOptions__Group__4 : rule__CSharpOptions__Group__4__Impl rule__CSharpOptions__Group__5 ;
    public final void rule__CSharpOptions__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1572:1: ( rule__CSharpOptions__Group__4__Impl rule__CSharpOptions__Group__5 )
            // InternalMORA.g:1573:2: rule__CSharpOptions__Group__4__Impl rule__CSharpOptions__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__CSharpOptions__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__4"


    // $ANTLR start "rule__CSharpOptions__Group__4__Impl"
    // InternalMORA.g:1580:1: rule__CSharpOptions__Group__4__Impl : ( ( rule__CSharpOptions__BaseNamespaceAssignment_4 ) ) ;
    public final void rule__CSharpOptions__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1584:1: ( ( ( rule__CSharpOptions__BaseNamespaceAssignment_4 ) ) )
            // InternalMORA.g:1585:1: ( ( rule__CSharpOptions__BaseNamespaceAssignment_4 ) )
            {
            // InternalMORA.g:1585:1: ( ( rule__CSharpOptions__BaseNamespaceAssignment_4 ) )
            // InternalMORA.g:1586:2: ( rule__CSharpOptions__BaseNamespaceAssignment_4 )
            {
             before(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceAssignment_4()); 
            // InternalMORA.g:1587:2: ( rule__CSharpOptions__BaseNamespaceAssignment_4 )
            // InternalMORA.g:1587:3: rule__CSharpOptions__BaseNamespaceAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__CSharpOptions__BaseNamespaceAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__4__Impl"


    // $ANTLR start "rule__CSharpOptions__Group__5"
    // InternalMORA.g:1595:1: rule__CSharpOptions__Group__5 : rule__CSharpOptions__Group__5__Impl ;
    public final void rule__CSharpOptions__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1599:1: ( rule__CSharpOptions__Group__5__Impl )
            // InternalMORA.g:1600:2: rule__CSharpOptions__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CSharpOptions__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__5"


    // $ANTLR start "rule__CSharpOptions__Group__5__Impl"
    // InternalMORA.g:1606:1: rule__CSharpOptions__Group__5__Impl : ( '}' ) ;
    public final void rule__CSharpOptions__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1610:1: ( ( '}' ) )
            // InternalMORA.g:1611:1: ( '}' )
            {
            // InternalMORA.g:1611:1: ( '}' )
            // InternalMORA.g:1612:2: '}'
            {
             before(grammarAccess.getCSharpOptionsAccess().getRightCurlyBracketKeyword_5()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getCSharpOptionsAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__Group__5__Impl"


    // $ANTLR start "rule__CppOptions__Group__0"
    // InternalMORA.g:1622:1: rule__CppOptions__Group__0 : rule__CppOptions__Group__0__Impl rule__CppOptions__Group__1 ;
    public final void rule__CppOptions__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1626:1: ( rule__CppOptions__Group__0__Impl rule__CppOptions__Group__1 )
            // InternalMORA.g:1627:2: rule__CppOptions__Group__0__Impl rule__CppOptions__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__CppOptions__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__0"


    // $ANTLR start "rule__CppOptions__Group__0__Impl"
    // InternalMORA.g:1634:1: rule__CppOptions__Group__0__Impl : ( 'cpp' ) ;
    public final void rule__CppOptions__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1638:1: ( ( 'cpp' ) )
            // InternalMORA.g:1639:1: ( 'cpp' )
            {
            // InternalMORA.g:1639:1: ( 'cpp' )
            // InternalMORA.g:1640:2: 'cpp'
            {
             before(grammarAccess.getCppOptionsAccess().getCppKeyword_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getCppOptionsAccess().getCppKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__0__Impl"


    // $ANTLR start "rule__CppOptions__Group__1"
    // InternalMORA.g:1649:1: rule__CppOptions__Group__1 : rule__CppOptions__Group__1__Impl rule__CppOptions__Group__2 ;
    public final void rule__CppOptions__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1653:1: ( rule__CppOptions__Group__1__Impl rule__CppOptions__Group__2 )
            // InternalMORA.g:1654:2: rule__CppOptions__Group__1__Impl rule__CppOptions__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__CppOptions__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__1"


    // $ANTLR start "rule__CppOptions__Group__1__Impl"
    // InternalMORA.g:1661:1: rule__CppOptions__Group__1__Impl : ( '{' ) ;
    public final void rule__CppOptions__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1665:1: ( ( '{' ) )
            // InternalMORA.g:1666:1: ( '{' )
            {
            // InternalMORA.g:1666:1: ( '{' )
            // InternalMORA.g:1667:2: '{'
            {
             before(grammarAccess.getCppOptionsAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getCppOptionsAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__1__Impl"


    // $ANTLR start "rule__CppOptions__Group__2"
    // InternalMORA.g:1676:1: rule__CppOptions__Group__2 : rule__CppOptions__Group__2__Impl rule__CppOptions__Group__3 ;
    public final void rule__CppOptions__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1680:1: ( rule__CppOptions__Group__2__Impl rule__CppOptions__Group__3 )
            // InternalMORA.g:1681:2: rule__CppOptions__Group__2__Impl rule__CppOptions__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__CppOptions__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__2"


    // $ANTLR start "rule__CppOptions__Group__2__Impl"
    // InternalMORA.g:1688:1: rule__CppOptions__Group__2__Impl : ( 'base-namespace' ) ;
    public final void rule__CppOptions__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1692:1: ( ( 'base-namespace' ) )
            // InternalMORA.g:1693:1: ( 'base-namespace' )
            {
            // InternalMORA.g:1693:1: ( 'base-namespace' )
            // InternalMORA.g:1694:2: 'base-namespace'
            {
             before(grammarAccess.getCppOptionsAccess().getBaseNamespaceKeyword_2()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getCppOptionsAccess().getBaseNamespaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__2__Impl"


    // $ANTLR start "rule__CppOptions__Group__3"
    // InternalMORA.g:1703:1: rule__CppOptions__Group__3 : rule__CppOptions__Group__3__Impl rule__CppOptions__Group__4 ;
    public final void rule__CppOptions__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1707:1: ( rule__CppOptions__Group__3__Impl rule__CppOptions__Group__4 )
            // InternalMORA.g:1708:2: rule__CppOptions__Group__3__Impl rule__CppOptions__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__CppOptions__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__3"


    // $ANTLR start "rule__CppOptions__Group__3__Impl"
    // InternalMORA.g:1715:1: rule__CppOptions__Group__3__Impl : ( '=' ) ;
    public final void rule__CppOptions__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1719:1: ( ( '=' ) )
            // InternalMORA.g:1720:1: ( '=' )
            {
            // InternalMORA.g:1720:1: ( '=' )
            // InternalMORA.g:1721:2: '='
            {
             before(grammarAccess.getCppOptionsAccess().getEqualsSignKeyword_3()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getCppOptionsAccess().getEqualsSignKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__3__Impl"


    // $ANTLR start "rule__CppOptions__Group__4"
    // InternalMORA.g:1730:1: rule__CppOptions__Group__4 : rule__CppOptions__Group__4__Impl rule__CppOptions__Group__5 ;
    public final void rule__CppOptions__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1734:1: ( rule__CppOptions__Group__4__Impl rule__CppOptions__Group__5 )
            // InternalMORA.g:1735:2: rule__CppOptions__Group__4__Impl rule__CppOptions__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__CppOptions__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__4"


    // $ANTLR start "rule__CppOptions__Group__4__Impl"
    // InternalMORA.g:1742:1: rule__CppOptions__Group__4__Impl : ( ( rule__CppOptions__BaseNamespaceAssignment_4 ) ) ;
    public final void rule__CppOptions__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1746:1: ( ( ( rule__CppOptions__BaseNamespaceAssignment_4 ) ) )
            // InternalMORA.g:1747:1: ( ( rule__CppOptions__BaseNamespaceAssignment_4 ) )
            {
            // InternalMORA.g:1747:1: ( ( rule__CppOptions__BaseNamespaceAssignment_4 ) )
            // InternalMORA.g:1748:2: ( rule__CppOptions__BaseNamespaceAssignment_4 )
            {
             before(grammarAccess.getCppOptionsAccess().getBaseNamespaceAssignment_4()); 
            // InternalMORA.g:1749:2: ( rule__CppOptions__BaseNamespaceAssignment_4 )
            // InternalMORA.g:1749:3: rule__CppOptions__BaseNamespaceAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__CppOptions__BaseNamespaceAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getCppOptionsAccess().getBaseNamespaceAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__4__Impl"


    // $ANTLR start "rule__CppOptions__Group__5"
    // InternalMORA.g:1757:1: rule__CppOptions__Group__5 : rule__CppOptions__Group__5__Impl ;
    public final void rule__CppOptions__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1761:1: ( rule__CppOptions__Group__5__Impl )
            // InternalMORA.g:1762:2: rule__CppOptions__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CppOptions__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__5"


    // $ANTLR start "rule__CppOptions__Group__5__Impl"
    // InternalMORA.g:1768:1: rule__CppOptions__Group__5__Impl : ( '}' ) ;
    public final void rule__CppOptions__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1772:1: ( ( '}' ) )
            // InternalMORA.g:1773:1: ( '}' )
            {
            // InternalMORA.g:1773:1: ( '}' )
            // InternalMORA.g:1774:2: '}'
            {
             before(grammarAccess.getCppOptionsAccess().getRightCurlyBracketKeyword_5()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getCppOptionsAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__Group__5__Impl"


    // $ANTLR start "rule__Annotation__Group__0"
    // InternalMORA.g:1784:1: rule__Annotation__Group__0 : rule__Annotation__Group__0__Impl rule__Annotation__Group__1 ;
    public final void rule__Annotation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1788:1: ( rule__Annotation__Group__0__Impl rule__Annotation__Group__1 )
            // InternalMORA.g:1789:2: rule__Annotation__Group__0__Impl rule__Annotation__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Annotation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Annotation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__0"


    // $ANTLR start "rule__Annotation__Group__0__Impl"
    // InternalMORA.g:1796:1: rule__Annotation__Group__0__Impl : ( '@' ) ;
    public final void rule__Annotation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1800:1: ( ( '@' ) )
            // InternalMORA.g:1801:1: ( '@' )
            {
            // InternalMORA.g:1801:1: ( '@' )
            // InternalMORA.g:1802:2: '@'
            {
             before(grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__0__Impl"


    // $ANTLR start "rule__Annotation__Group__1"
    // InternalMORA.g:1811:1: rule__Annotation__Group__1 : rule__Annotation__Group__1__Impl rule__Annotation__Group__2 ;
    public final void rule__Annotation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1815:1: ( rule__Annotation__Group__1__Impl rule__Annotation__Group__2 )
            // InternalMORA.g:1816:2: rule__Annotation__Group__1__Impl rule__Annotation__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Annotation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Annotation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__1"


    // $ANTLR start "rule__Annotation__Group__1__Impl"
    // InternalMORA.g:1823:1: rule__Annotation__Group__1__Impl : ( ( rule__Annotation__NameAssignment_1 ) ) ;
    public final void rule__Annotation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1827:1: ( ( ( rule__Annotation__NameAssignment_1 ) ) )
            // InternalMORA.g:1828:1: ( ( rule__Annotation__NameAssignment_1 ) )
            {
            // InternalMORA.g:1828:1: ( ( rule__Annotation__NameAssignment_1 ) )
            // InternalMORA.g:1829:2: ( rule__Annotation__NameAssignment_1 )
            {
             before(grammarAccess.getAnnotationAccess().getNameAssignment_1()); 
            // InternalMORA.g:1830:2: ( rule__Annotation__NameAssignment_1 )
            // InternalMORA.g:1830:3: rule__Annotation__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Annotation__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getAnnotationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__1__Impl"


    // $ANTLR start "rule__Annotation__Group__2"
    // InternalMORA.g:1838:1: rule__Annotation__Group__2 : rule__Annotation__Group__2__Impl ;
    public final void rule__Annotation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1842:1: ( rule__Annotation__Group__2__Impl )
            // InternalMORA.g:1843:2: rule__Annotation__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Annotation__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__2"


    // $ANTLR start "rule__Annotation__Group__2__Impl"
    // InternalMORA.g:1849:1: rule__Annotation__Group__2__Impl : ( ( rule__Annotation__Group_2__0 )? ) ;
    public final void rule__Annotation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1853:1: ( ( ( rule__Annotation__Group_2__0 )? ) )
            // InternalMORA.g:1854:1: ( ( rule__Annotation__Group_2__0 )? )
            {
            // InternalMORA.g:1854:1: ( ( rule__Annotation__Group_2__0 )? )
            // InternalMORA.g:1855:2: ( rule__Annotation__Group_2__0 )?
            {
             before(grammarAccess.getAnnotationAccess().getGroup_2()); 
            // InternalMORA.g:1856:2: ( rule__Annotation__Group_2__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalMORA.g:1856:3: rule__Annotation__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Annotation__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAnnotationAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group__2__Impl"


    // $ANTLR start "rule__Annotation__Group_2__0"
    // InternalMORA.g:1865:1: rule__Annotation__Group_2__0 : rule__Annotation__Group_2__0__Impl rule__Annotation__Group_2__1 ;
    public final void rule__Annotation__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1869:1: ( rule__Annotation__Group_2__0__Impl rule__Annotation__Group_2__1 )
            // InternalMORA.g:1870:2: rule__Annotation__Group_2__0__Impl rule__Annotation__Group_2__1
            {
            pushFollow(FOLLOW_10);
            rule__Annotation__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Annotation__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group_2__0"


    // $ANTLR start "rule__Annotation__Group_2__0__Impl"
    // InternalMORA.g:1877:1: rule__Annotation__Group_2__0__Impl : ( '=' ) ;
    public final void rule__Annotation__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1881:1: ( ( '=' ) )
            // InternalMORA.g:1882:1: ( '=' )
            {
            // InternalMORA.g:1882:1: ( '=' )
            // InternalMORA.g:1883:2: '='
            {
             before(grammarAccess.getAnnotationAccess().getEqualsSignKeyword_2_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getAnnotationAccess().getEqualsSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group_2__0__Impl"


    // $ANTLR start "rule__Annotation__Group_2__1"
    // InternalMORA.g:1892:1: rule__Annotation__Group_2__1 : rule__Annotation__Group_2__1__Impl ;
    public final void rule__Annotation__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1896:1: ( rule__Annotation__Group_2__1__Impl )
            // InternalMORA.g:1897:2: rule__Annotation__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Annotation__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group_2__1"


    // $ANTLR start "rule__Annotation__Group_2__1__Impl"
    // InternalMORA.g:1903:1: rule__Annotation__Group_2__1__Impl : ( ( rule__Annotation__ValueAssignment_2_1 ) ) ;
    public final void rule__Annotation__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1907:1: ( ( ( rule__Annotation__ValueAssignment_2_1 ) ) )
            // InternalMORA.g:1908:1: ( ( rule__Annotation__ValueAssignment_2_1 ) )
            {
            // InternalMORA.g:1908:1: ( ( rule__Annotation__ValueAssignment_2_1 ) )
            // InternalMORA.g:1909:2: ( rule__Annotation__ValueAssignment_2_1 )
            {
             before(grammarAccess.getAnnotationAccess().getValueAssignment_2_1()); 
            // InternalMORA.g:1910:2: ( rule__Annotation__ValueAssignment_2_1 )
            // InternalMORA.g:1910:3: rule__Annotation__ValueAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Annotation__ValueAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getAnnotationAccess().getValueAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__Group_2__1__Impl"


    // $ANTLR start "rule__StructDecl__Group__0"
    // InternalMORA.g:1919:1: rule__StructDecl__Group__0 : rule__StructDecl__Group__0__Impl rule__StructDecl__Group__1 ;
    public final void rule__StructDecl__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1923:1: ( rule__StructDecl__Group__0__Impl rule__StructDecl__Group__1 )
            // InternalMORA.g:1924:2: rule__StructDecl__Group__0__Impl rule__StructDecl__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__StructDecl__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__0"


    // $ANTLR start "rule__StructDecl__Group__0__Impl"
    // InternalMORA.g:1931:1: rule__StructDecl__Group__0__Impl : ( ( rule__StructDecl__DocAssignment_0 )? ) ;
    public final void rule__StructDecl__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1935:1: ( ( ( rule__StructDecl__DocAssignment_0 )? ) )
            // InternalMORA.g:1936:1: ( ( rule__StructDecl__DocAssignment_0 )? )
            {
            // InternalMORA.g:1936:1: ( ( rule__StructDecl__DocAssignment_0 )? )
            // InternalMORA.g:1937:2: ( rule__StructDecl__DocAssignment_0 )?
            {
             before(grammarAccess.getStructDeclAccess().getDocAssignment_0()); 
            // InternalMORA.g:1938:2: ( rule__StructDecl__DocAssignment_0 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ML_COMMENT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalMORA.g:1938:3: rule__StructDecl__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__StructDecl__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getStructDeclAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__0__Impl"


    // $ANTLR start "rule__StructDecl__Group__1"
    // InternalMORA.g:1946:1: rule__StructDecl__Group__1 : rule__StructDecl__Group__1__Impl rule__StructDecl__Group__2 ;
    public final void rule__StructDecl__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1950:1: ( rule__StructDecl__Group__1__Impl rule__StructDecl__Group__2 )
            // InternalMORA.g:1951:2: rule__StructDecl__Group__1__Impl rule__StructDecl__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__StructDecl__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__1"


    // $ANTLR start "rule__StructDecl__Group__1__Impl"
    // InternalMORA.g:1958:1: rule__StructDecl__Group__1__Impl : ( ( rule__StructDecl__AnnoAssignment_1 )* ) ;
    public final void rule__StructDecl__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1962:1: ( ( ( rule__StructDecl__AnnoAssignment_1 )* ) )
            // InternalMORA.g:1963:1: ( ( rule__StructDecl__AnnoAssignment_1 )* )
            {
            // InternalMORA.g:1963:1: ( ( rule__StructDecl__AnnoAssignment_1 )* )
            // InternalMORA.g:1964:2: ( rule__StructDecl__AnnoAssignment_1 )*
            {
             before(grammarAccess.getStructDeclAccess().getAnnoAssignment_1()); 
            // InternalMORA.g:1965:2: ( rule__StructDecl__AnnoAssignment_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMORA.g:1965:3: rule__StructDecl__AnnoAssignment_1
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__StructDecl__AnnoAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getStructDeclAccess().getAnnoAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__1__Impl"


    // $ANTLR start "rule__StructDecl__Group__2"
    // InternalMORA.g:1973:1: rule__StructDecl__Group__2 : rule__StructDecl__Group__2__Impl rule__StructDecl__Group__3 ;
    public final void rule__StructDecl__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1977:1: ( rule__StructDecl__Group__2__Impl rule__StructDecl__Group__3 )
            // InternalMORA.g:1978:2: rule__StructDecl__Group__2__Impl rule__StructDecl__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__StructDecl__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__2"


    // $ANTLR start "rule__StructDecl__Group__2__Impl"
    // InternalMORA.g:1985:1: rule__StructDecl__Group__2__Impl : ( 'struct' ) ;
    public final void rule__StructDecl__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:1989:1: ( ( 'struct' ) )
            // InternalMORA.g:1990:1: ( 'struct' )
            {
            // InternalMORA.g:1990:1: ( 'struct' )
            // InternalMORA.g:1991:2: 'struct'
            {
             before(grammarAccess.getStructDeclAccess().getStructKeyword_2()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getStructDeclAccess().getStructKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__2__Impl"


    // $ANTLR start "rule__StructDecl__Group__3"
    // InternalMORA.g:2000:1: rule__StructDecl__Group__3 : rule__StructDecl__Group__3__Impl rule__StructDecl__Group__4 ;
    public final void rule__StructDecl__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2004:1: ( rule__StructDecl__Group__3__Impl rule__StructDecl__Group__4 )
            // InternalMORA.g:2005:2: rule__StructDecl__Group__3__Impl rule__StructDecl__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__StructDecl__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__3"


    // $ANTLR start "rule__StructDecl__Group__3__Impl"
    // InternalMORA.g:2012:1: rule__StructDecl__Group__3__Impl : ( ( rule__StructDecl__NameAssignment_3 ) ) ;
    public final void rule__StructDecl__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2016:1: ( ( ( rule__StructDecl__NameAssignment_3 ) ) )
            // InternalMORA.g:2017:1: ( ( rule__StructDecl__NameAssignment_3 ) )
            {
            // InternalMORA.g:2017:1: ( ( rule__StructDecl__NameAssignment_3 ) )
            // InternalMORA.g:2018:2: ( rule__StructDecl__NameAssignment_3 )
            {
             before(grammarAccess.getStructDeclAccess().getNameAssignment_3()); 
            // InternalMORA.g:2019:2: ( rule__StructDecl__NameAssignment_3 )
            // InternalMORA.g:2019:3: rule__StructDecl__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__StructDecl__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getStructDeclAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__3__Impl"


    // $ANTLR start "rule__StructDecl__Group__4"
    // InternalMORA.g:2027:1: rule__StructDecl__Group__4 : rule__StructDecl__Group__4__Impl rule__StructDecl__Group__5 ;
    public final void rule__StructDecl__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2031:1: ( rule__StructDecl__Group__4__Impl rule__StructDecl__Group__5 )
            // InternalMORA.g:2032:2: rule__StructDecl__Group__4__Impl rule__StructDecl__Group__5
            {
            pushFollow(FOLLOW_19);
            rule__StructDecl__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__4"


    // $ANTLR start "rule__StructDecl__Group__4__Impl"
    // InternalMORA.g:2039:1: rule__StructDecl__Group__4__Impl : ( '{' ) ;
    public final void rule__StructDecl__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2043:1: ( ( '{' ) )
            // InternalMORA.g:2044:1: ( '{' )
            {
            // InternalMORA.g:2044:1: ( '{' )
            // InternalMORA.g:2045:2: '{'
            {
             before(grammarAccess.getStructDeclAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getStructDeclAccess().getLeftCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__4__Impl"


    // $ANTLR start "rule__StructDecl__Group__5"
    // InternalMORA.g:2054:1: rule__StructDecl__Group__5 : rule__StructDecl__Group__5__Impl rule__StructDecl__Group__6 ;
    public final void rule__StructDecl__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2058:1: ( rule__StructDecl__Group__5__Impl rule__StructDecl__Group__6 )
            // InternalMORA.g:2059:2: rule__StructDecl__Group__5__Impl rule__StructDecl__Group__6
            {
            pushFollow(FOLLOW_19);
            rule__StructDecl__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__5"


    // $ANTLR start "rule__StructDecl__Group__5__Impl"
    // InternalMORA.g:2066:1: rule__StructDecl__Group__5__Impl : ( ( rule__StructDecl__Group_5__0 )* ) ;
    public final void rule__StructDecl__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2070:1: ( ( ( rule__StructDecl__Group_5__0 )* ) )
            // InternalMORA.g:2071:1: ( ( rule__StructDecl__Group_5__0 )* )
            {
            // InternalMORA.g:2071:1: ( ( rule__StructDecl__Group_5__0 )* )
            // InternalMORA.g:2072:2: ( rule__StructDecl__Group_5__0 )*
            {
             before(grammarAccess.getStructDeclAccess().getGroup_5()); 
            // InternalMORA.g:2073:2: ( rule__StructDecl__Group_5__0 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID||LA21_0==RULE_ML_COMMENT||(LA21_0>=13 && LA21_0<=21)||LA21_0==34) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalMORA.g:2073:3: rule__StructDecl__Group_5__0
            	    {
            	    pushFollow(FOLLOW_20);
            	    rule__StructDecl__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getStructDeclAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__5__Impl"


    // $ANTLR start "rule__StructDecl__Group__6"
    // InternalMORA.g:2081:1: rule__StructDecl__Group__6 : rule__StructDecl__Group__6__Impl ;
    public final void rule__StructDecl__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2085:1: ( rule__StructDecl__Group__6__Impl )
            // InternalMORA.g:2086:2: rule__StructDecl__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__StructDecl__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__6"


    // $ANTLR start "rule__StructDecl__Group__6__Impl"
    // InternalMORA.g:2092:1: rule__StructDecl__Group__6__Impl : ( '}' ) ;
    public final void rule__StructDecl__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2096:1: ( ( '}' ) )
            // InternalMORA.g:2097:1: ( '}' )
            {
            // InternalMORA.g:2097:1: ( '}' )
            // InternalMORA.g:2098:2: '}'
            {
             before(grammarAccess.getStructDeclAccess().getRightCurlyBracketKeyword_6()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getStructDeclAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group__6__Impl"


    // $ANTLR start "rule__StructDecl__Group_5__0"
    // InternalMORA.g:2108:1: rule__StructDecl__Group_5__0 : rule__StructDecl__Group_5__0__Impl rule__StructDecl__Group_5__1 ;
    public final void rule__StructDecl__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2112:1: ( rule__StructDecl__Group_5__0__Impl rule__StructDecl__Group_5__1 )
            // InternalMORA.g:2113:2: rule__StructDecl__Group_5__0__Impl rule__StructDecl__Group_5__1
            {
            pushFollow(FOLLOW_9);
            rule__StructDecl__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StructDecl__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group_5__0"


    // $ANTLR start "rule__StructDecl__Group_5__0__Impl"
    // InternalMORA.g:2120:1: rule__StructDecl__Group_5__0__Impl : ( ( rule__StructDecl__MemberAssignment_5_0 ) ) ;
    public final void rule__StructDecl__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2124:1: ( ( ( rule__StructDecl__MemberAssignment_5_0 ) ) )
            // InternalMORA.g:2125:1: ( ( rule__StructDecl__MemberAssignment_5_0 ) )
            {
            // InternalMORA.g:2125:1: ( ( rule__StructDecl__MemberAssignment_5_0 ) )
            // InternalMORA.g:2126:2: ( rule__StructDecl__MemberAssignment_5_0 )
            {
             before(grammarAccess.getStructDeclAccess().getMemberAssignment_5_0()); 
            // InternalMORA.g:2127:2: ( rule__StructDecl__MemberAssignment_5_0 )
            // InternalMORA.g:2127:3: rule__StructDecl__MemberAssignment_5_0
            {
            pushFollow(FOLLOW_2);
            rule__StructDecl__MemberAssignment_5_0();

            state._fsp--;


            }

             after(grammarAccess.getStructDeclAccess().getMemberAssignment_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group_5__0__Impl"


    // $ANTLR start "rule__StructDecl__Group_5__1"
    // InternalMORA.g:2135:1: rule__StructDecl__Group_5__1 : rule__StructDecl__Group_5__1__Impl ;
    public final void rule__StructDecl__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2139:1: ( rule__StructDecl__Group_5__1__Impl )
            // InternalMORA.g:2140:2: rule__StructDecl__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__StructDecl__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group_5__1"


    // $ANTLR start "rule__StructDecl__Group_5__1__Impl"
    // InternalMORA.g:2146:1: rule__StructDecl__Group_5__1__Impl : ( ( ';' )? ) ;
    public final void rule__StructDecl__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2150:1: ( ( ( ';' )? ) )
            // InternalMORA.g:2151:1: ( ( ';' )? )
            {
            // InternalMORA.g:2151:1: ( ( ';' )? )
            // InternalMORA.g:2152:2: ( ';' )?
            {
             before(grammarAccess.getStructDeclAccess().getSemicolonKeyword_5_1()); 
            // InternalMORA.g:2153:2: ( ';' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==25) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalMORA.g:2153:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getStructDeclAccess().getSemicolonKeyword_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__Group_5__1__Impl"


    // $ANTLR start "rule__Member__Group__0"
    // InternalMORA.g:2162:1: rule__Member__Group__0 : rule__Member__Group__0__Impl rule__Member__Group__1 ;
    public final void rule__Member__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2166:1: ( rule__Member__Group__0__Impl rule__Member__Group__1 )
            // InternalMORA.g:2167:2: rule__Member__Group__0__Impl rule__Member__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__Member__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Member__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__0"


    // $ANTLR start "rule__Member__Group__0__Impl"
    // InternalMORA.g:2174:1: rule__Member__Group__0__Impl : ( ( rule__Member__DocAssignment_0 )? ) ;
    public final void rule__Member__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2178:1: ( ( ( rule__Member__DocAssignment_0 )? ) )
            // InternalMORA.g:2179:1: ( ( rule__Member__DocAssignment_0 )? )
            {
            // InternalMORA.g:2179:1: ( ( rule__Member__DocAssignment_0 )? )
            // InternalMORA.g:2180:2: ( rule__Member__DocAssignment_0 )?
            {
             before(grammarAccess.getMemberAccess().getDocAssignment_0()); 
            // InternalMORA.g:2181:2: ( rule__Member__DocAssignment_0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ML_COMMENT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalMORA.g:2181:3: rule__Member__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Member__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMemberAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__0__Impl"


    // $ANTLR start "rule__Member__Group__1"
    // InternalMORA.g:2189:1: rule__Member__Group__1 : rule__Member__Group__1__Impl rule__Member__Group__2 ;
    public final void rule__Member__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2193:1: ( rule__Member__Group__1__Impl rule__Member__Group__2 )
            // InternalMORA.g:2194:2: rule__Member__Group__1__Impl rule__Member__Group__2
            {
            pushFollow(FOLLOW_21);
            rule__Member__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Member__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__1"


    // $ANTLR start "rule__Member__Group__1__Impl"
    // InternalMORA.g:2201:1: rule__Member__Group__1__Impl : ( ( rule__Member__AnnoAssignment_1 )* ) ;
    public final void rule__Member__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2205:1: ( ( ( rule__Member__AnnoAssignment_1 )* ) )
            // InternalMORA.g:2206:1: ( ( rule__Member__AnnoAssignment_1 )* )
            {
            // InternalMORA.g:2206:1: ( ( rule__Member__AnnoAssignment_1 )* )
            // InternalMORA.g:2207:2: ( rule__Member__AnnoAssignment_1 )*
            {
             before(grammarAccess.getMemberAccess().getAnnoAssignment_1()); 
            // InternalMORA.g:2208:2: ( rule__Member__AnnoAssignment_1 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==34) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalMORA.g:2208:3: rule__Member__AnnoAssignment_1
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__Member__AnnoAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getMemberAccess().getAnnoAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__1__Impl"


    // $ANTLR start "rule__Member__Group__2"
    // InternalMORA.g:2216:1: rule__Member__Group__2 : rule__Member__Group__2__Impl rule__Member__Group__3 ;
    public final void rule__Member__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2220:1: ( rule__Member__Group__2__Impl rule__Member__Group__3 )
            // InternalMORA.g:2221:2: rule__Member__Group__2__Impl rule__Member__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Member__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Member__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__2"


    // $ANTLR start "rule__Member__Group__2__Impl"
    // InternalMORA.g:2228:1: rule__Member__Group__2__Impl : ( ( rule__Member__Alternatives_2 ) ) ;
    public final void rule__Member__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2232:1: ( ( ( rule__Member__Alternatives_2 ) ) )
            // InternalMORA.g:2233:1: ( ( rule__Member__Alternatives_2 ) )
            {
            // InternalMORA.g:2233:1: ( ( rule__Member__Alternatives_2 ) )
            // InternalMORA.g:2234:2: ( rule__Member__Alternatives_2 )
            {
             before(grammarAccess.getMemberAccess().getAlternatives_2()); 
            // InternalMORA.g:2235:2: ( rule__Member__Alternatives_2 )
            // InternalMORA.g:2235:3: rule__Member__Alternatives_2
            {
            pushFollow(FOLLOW_2);
            rule__Member__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getMemberAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__2__Impl"


    // $ANTLR start "rule__Member__Group__3"
    // InternalMORA.g:2243:1: rule__Member__Group__3 : rule__Member__Group__3__Impl ;
    public final void rule__Member__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2247:1: ( rule__Member__Group__3__Impl )
            // InternalMORA.g:2248:2: rule__Member__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Member__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__3"


    // $ANTLR start "rule__Member__Group__3__Impl"
    // InternalMORA.g:2254:1: rule__Member__Group__3__Impl : ( ( rule__Member__NameAssignment_3 ) ) ;
    public final void rule__Member__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2258:1: ( ( ( rule__Member__NameAssignment_3 ) ) )
            // InternalMORA.g:2259:1: ( ( rule__Member__NameAssignment_3 ) )
            {
            // InternalMORA.g:2259:1: ( ( rule__Member__NameAssignment_3 ) )
            // InternalMORA.g:2260:2: ( rule__Member__NameAssignment_3 )
            {
             before(grammarAccess.getMemberAccess().getNameAssignment_3()); 
            // InternalMORA.g:2261:2: ( rule__Member__NameAssignment_3 )
            // InternalMORA.g:2261:3: rule__Member__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Member__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getMemberAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__Group__3__Impl"


    // $ANTLR start "rule__EnumDecl__Group__0"
    // InternalMORA.g:2270:1: rule__EnumDecl__Group__0 : rule__EnumDecl__Group__0__Impl rule__EnumDecl__Group__1 ;
    public final void rule__EnumDecl__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2274:1: ( rule__EnumDecl__Group__0__Impl rule__EnumDecl__Group__1 )
            // InternalMORA.g:2275:2: rule__EnumDecl__Group__0__Impl rule__EnumDecl__Group__1
            {
            pushFollow(FOLLOW_22);
            rule__EnumDecl__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__0"


    // $ANTLR start "rule__EnumDecl__Group__0__Impl"
    // InternalMORA.g:2282:1: rule__EnumDecl__Group__0__Impl : ( ( rule__EnumDecl__DocAssignment_0 )? ) ;
    public final void rule__EnumDecl__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2286:1: ( ( ( rule__EnumDecl__DocAssignment_0 )? ) )
            // InternalMORA.g:2287:1: ( ( rule__EnumDecl__DocAssignment_0 )? )
            {
            // InternalMORA.g:2287:1: ( ( rule__EnumDecl__DocAssignment_0 )? )
            // InternalMORA.g:2288:2: ( rule__EnumDecl__DocAssignment_0 )?
            {
             before(grammarAccess.getEnumDeclAccess().getDocAssignment_0()); 
            // InternalMORA.g:2289:2: ( rule__EnumDecl__DocAssignment_0 )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ML_COMMENT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalMORA.g:2289:3: rule__EnumDecl__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EnumDecl__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEnumDeclAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__0__Impl"


    // $ANTLR start "rule__EnumDecl__Group__1"
    // InternalMORA.g:2297:1: rule__EnumDecl__Group__1 : rule__EnumDecl__Group__1__Impl rule__EnumDecl__Group__2 ;
    public final void rule__EnumDecl__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2301:1: ( rule__EnumDecl__Group__1__Impl rule__EnumDecl__Group__2 )
            // InternalMORA.g:2302:2: rule__EnumDecl__Group__1__Impl rule__EnumDecl__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__EnumDecl__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__1"


    // $ANTLR start "rule__EnumDecl__Group__1__Impl"
    // InternalMORA.g:2309:1: rule__EnumDecl__Group__1__Impl : ( 'enum' ) ;
    public final void rule__EnumDecl__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2313:1: ( ( 'enum' ) )
            // InternalMORA.g:2314:1: ( 'enum' )
            {
            // InternalMORA.g:2314:1: ( 'enum' )
            // InternalMORA.g:2315:2: 'enum'
            {
             before(grammarAccess.getEnumDeclAccess().getEnumKeyword_1()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getEnumDeclAccess().getEnumKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__1__Impl"


    // $ANTLR start "rule__EnumDecl__Group__2"
    // InternalMORA.g:2324:1: rule__EnumDecl__Group__2 : rule__EnumDecl__Group__2__Impl rule__EnumDecl__Group__3 ;
    public final void rule__EnumDecl__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2328:1: ( rule__EnumDecl__Group__2__Impl rule__EnumDecl__Group__3 )
            // InternalMORA.g:2329:2: rule__EnumDecl__Group__2__Impl rule__EnumDecl__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__EnumDecl__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__2"


    // $ANTLR start "rule__EnumDecl__Group__2__Impl"
    // InternalMORA.g:2336:1: rule__EnumDecl__Group__2__Impl : ( ( rule__EnumDecl__NameAssignment_2 ) ) ;
    public final void rule__EnumDecl__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2340:1: ( ( ( rule__EnumDecl__NameAssignment_2 ) ) )
            // InternalMORA.g:2341:1: ( ( rule__EnumDecl__NameAssignment_2 ) )
            {
            // InternalMORA.g:2341:1: ( ( rule__EnumDecl__NameAssignment_2 ) )
            // InternalMORA.g:2342:2: ( rule__EnumDecl__NameAssignment_2 )
            {
             before(grammarAccess.getEnumDeclAccess().getNameAssignment_2()); 
            // InternalMORA.g:2343:2: ( rule__EnumDecl__NameAssignment_2 )
            // InternalMORA.g:2343:3: rule__EnumDecl__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EnumDecl__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEnumDeclAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__2__Impl"


    // $ANTLR start "rule__EnumDecl__Group__3"
    // InternalMORA.g:2351:1: rule__EnumDecl__Group__3 : rule__EnumDecl__Group__3__Impl rule__EnumDecl__Group__4 ;
    public final void rule__EnumDecl__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2355:1: ( rule__EnumDecl__Group__3__Impl rule__EnumDecl__Group__4 )
            // InternalMORA.g:2356:2: rule__EnumDecl__Group__3__Impl rule__EnumDecl__Group__4
            {
            pushFollow(FOLLOW_23);
            rule__EnumDecl__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__3"


    // $ANTLR start "rule__EnumDecl__Group__3__Impl"
    // InternalMORA.g:2363:1: rule__EnumDecl__Group__3__Impl : ( '{' ) ;
    public final void rule__EnumDecl__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2367:1: ( ( '{' ) )
            // InternalMORA.g:2368:1: ( '{' )
            {
            // InternalMORA.g:2368:1: ( '{' )
            // InternalMORA.g:2369:2: '{'
            {
             before(grammarAccess.getEnumDeclAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getEnumDeclAccess().getLeftCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__3__Impl"


    // $ANTLR start "rule__EnumDecl__Group__4"
    // InternalMORA.g:2378:1: rule__EnumDecl__Group__4 : rule__EnumDecl__Group__4__Impl rule__EnumDecl__Group__5 ;
    public final void rule__EnumDecl__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2382:1: ( rule__EnumDecl__Group__4__Impl rule__EnumDecl__Group__5 )
            // InternalMORA.g:2383:2: rule__EnumDecl__Group__4__Impl rule__EnumDecl__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__EnumDecl__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__4"


    // $ANTLR start "rule__EnumDecl__Group__4__Impl"
    // InternalMORA.g:2390:1: rule__EnumDecl__Group__4__Impl : ( ( ( rule__EnumDecl__Group_4__0 ) ) ( ( rule__EnumDecl__Group_4__0 )* ) ) ;
    public final void rule__EnumDecl__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2394:1: ( ( ( ( rule__EnumDecl__Group_4__0 ) ) ( ( rule__EnumDecl__Group_4__0 )* ) ) )
            // InternalMORA.g:2395:1: ( ( ( rule__EnumDecl__Group_4__0 ) ) ( ( rule__EnumDecl__Group_4__0 )* ) )
            {
            // InternalMORA.g:2395:1: ( ( ( rule__EnumDecl__Group_4__0 ) ) ( ( rule__EnumDecl__Group_4__0 )* ) )
            // InternalMORA.g:2396:2: ( ( rule__EnumDecl__Group_4__0 ) ) ( ( rule__EnumDecl__Group_4__0 )* )
            {
            // InternalMORA.g:2396:2: ( ( rule__EnumDecl__Group_4__0 ) )
            // InternalMORA.g:2397:3: ( rule__EnumDecl__Group_4__0 )
            {
             before(grammarAccess.getEnumDeclAccess().getGroup_4()); 
            // InternalMORA.g:2398:3: ( rule__EnumDecl__Group_4__0 )
            // InternalMORA.g:2398:4: rule__EnumDecl__Group_4__0
            {
            pushFollow(FOLLOW_24);
            rule__EnumDecl__Group_4__0();

            state._fsp--;


            }

             after(grammarAccess.getEnumDeclAccess().getGroup_4()); 

            }

            // InternalMORA.g:2401:2: ( ( rule__EnumDecl__Group_4__0 )* )
            // InternalMORA.g:2402:3: ( rule__EnumDecl__Group_4__0 )*
            {
             before(grammarAccess.getEnumDeclAccess().getGroup_4()); 
            // InternalMORA.g:2403:3: ( rule__EnumDecl__Group_4__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==RULE_ML_COMMENT) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalMORA.g:2403:4: rule__EnumDecl__Group_4__0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__EnumDecl__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getEnumDeclAccess().getGroup_4()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__4__Impl"


    // $ANTLR start "rule__EnumDecl__Group__5"
    // InternalMORA.g:2412:1: rule__EnumDecl__Group__5 : rule__EnumDecl__Group__5__Impl ;
    public final void rule__EnumDecl__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2416:1: ( rule__EnumDecl__Group__5__Impl )
            // InternalMORA.g:2417:2: rule__EnumDecl__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__5"


    // $ANTLR start "rule__EnumDecl__Group__5__Impl"
    // InternalMORA.g:2423:1: rule__EnumDecl__Group__5__Impl : ( '}' ) ;
    public final void rule__EnumDecl__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2427:1: ( ( '}' ) )
            // InternalMORA.g:2428:1: ( '}' )
            {
            // InternalMORA.g:2428:1: ( '}' )
            // InternalMORA.g:2429:2: '}'
            {
             before(grammarAccess.getEnumDeclAccess().getRightCurlyBracketKeyword_5()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getEnumDeclAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group__5__Impl"


    // $ANTLR start "rule__EnumDecl__Group_4__0"
    // InternalMORA.g:2439:1: rule__EnumDecl__Group_4__0 : rule__EnumDecl__Group_4__0__Impl rule__EnumDecl__Group_4__1 ;
    public final void rule__EnumDecl__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2443:1: ( rule__EnumDecl__Group_4__0__Impl rule__EnumDecl__Group_4__1 )
            // InternalMORA.g:2444:2: rule__EnumDecl__Group_4__0__Impl rule__EnumDecl__Group_4__1
            {
            pushFollow(FOLLOW_9);
            rule__EnumDecl__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group_4__0"


    // $ANTLR start "rule__EnumDecl__Group_4__0__Impl"
    // InternalMORA.g:2451:1: rule__EnumDecl__Group_4__0__Impl : ( ( rule__EnumDecl__LiteralsAssignment_4_0 ) ) ;
    public final void rule__EnumDecl__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2455:1: ( ( ( rule__EnumDecl__LiteralsAssignment_4_0 ) ) )
            // InternalMORA.g:2456:1: ( ( rule__EnumDecl__LiteralsAssignment_4_0 ) )
            {
            // InternalMORA.g:2456:1: ( ( rule__EnumDecl__LiteralsAssignment_4_0 ) )
            // InternalMORA.g:2457:2: ( rule__EnumDecl__LiteralsAssignment_4_0 )
            {
             before(grammarAccess.getEnumDeclAccess().getLiteralsAssignment_4_0()); 
            // InternalMORA.g:2458:2: ( rule__EnumDecl__LiteralsAssignment_4_0 )
            // InternalMORA.g:2458:3: rule__EnumDecl__LiteralsAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__EnumDecl__LiteralsAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getEnumDeclAccess().getLiteralsAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group_4__0__Impl"


    // $ANTLR start "rule__EnumDecl__Group_4__1"
    // InternalMORA.g:2466:1: rule__EnumDecl__Group_4__1 : rule__EnumDecl__Group_4__1__Impl ;
    public final void rule__EnumDecl__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2470:1: ( rule__EnumDecl__Group_4__1__Impl )
            // InternalMORA.g:2471:2: rule__EnumDecl__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumDecl__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group_4__1"


    // $ANTLR start "rule__EnumDecl__Group_4__1__Impl"
    // InternalMORA.g:2477:1: rule__EnumDecl__Group_4__1__Impl : ( ( ';' )? ) ;
    public final void rule__EnumDecl__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2481:1: ( ( ( ';' )? ) )
            // InternalMORA.g:2482:1: ( ( ';' )? )
            {
            // InternalMORA.g:2482:1: ( ( ';' )? )
            // InternalMORA.g:2483:2: ( ';' )?
            {
             before(grammarAccess.getEnumDeclAccess().getSemicolonKeyword_4_1()); 
            // InternalMORA.g:2484:2: ( ';' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==25) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalMORA.g:2484:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getEnumDeclAccess().getSemicolonKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__Group_4__1__Impl"


    // $ANTLR start "rule__Literal__Group__0"
    // InternalMORA.g:2493:1: rule__Literal__Group__0 : rule__Literal__Group__0__Impl rule__Literal__Group__1 ;
    public final void rule__Literal__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2497:1: ( rule__Literal__Group__0__Impl rule__Literal__Group__1 )
            // InternalMORA.g:2498:2: rule__Literal__Group__0__Impl rule__Literal__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__Literal__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Literal__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__0"


    // $ANTLR start "rule__Literal__Group__0__Impl"
    // InternalMORA.g:2505:1: rule__Literal__Group__0__Impl : ( ( rule__Literal__DocAssignment_0 )? ) ;
    public final void rule__Literal__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2509:1: ( ( ( rule__Literal__DocAssignment_0 )? ) )
            // InternalMORA.g:2510:1: ( ( rule__Literal__DocAssignment_0 )? )
            {
            // InternalMORA.g:2510:1: ( ( rule__Literal__DocAssignment_0 )? )
            // InternalMORA.g:2511:2: ( rule__Literal__DocAssignment_0 )?
            {
             before(grammarAccess.getLiteralAccess().getDocAssignment_0()); 
            // InternalMORA.g:2512:2: ( rule__Literal__DocAssignment_0 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_ML_COMMENT) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalMORA.g:2512:3: rule__Literal__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLiteralAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__0__Impl"


    // $ANTLR start "rule__Literal__Group__1"
    // InternalMORA.g:2520:1: rule__Literal__Group__1 : rule__Literal__Group__1__Impl rule__Literal__Group__2 ;
    public final void rule__Literal__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2524:1: ( rule__Literal__Group__1__Impl rule__Literal__Group__2 )
            // InternalMORA.g:2525:2: rule__Literal__Group__1__Impl rule__Literal__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Literal__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Literal__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__1"


    // $ANTLR start "rule__Literal__Group__1__Impl"
    // InternalMORA.g:2532:1: rule__Literal__Group__1__Impl : ( ( rule__Literal__NameAssignment_1 ) ) ;
    public final void rule__Literal__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2536:1: ( ( ( rule__Literal__NameAssignment_1 ) ) )
            // InternalMORA.g:2537:1: ( ( rule__Literal__NameAssignment_1 ) )
            {
            // InternalMORA.g:2537:1: ( ( rule__Literal__NameAssignment_1 ) )
            // InternalMORA.g:2538:2: ( rule__Literal__NameAssignment_1 )
            {
             before(grammarAccess.getLiteralAccess().getNameAssignment_1()); 
            // InternalMORA.g:2539:2: ( rule__Literal__NameAssignment_1 )
            // InternalMORA.g:2539:3: rule__Literal__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Literal__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getLiteralAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__1__Impl"


    // $ANTLR start "rule__Literal__Group__2"
    // InternalMORA.g:2547:1: rule__Literal__Group__2 : rule__Literal__Group__2__Impl ;
    public final void rule__Literal__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2551:1: ( rule__Literal__Group__2__Impl )
            // InternalMORA.g:2552:2: rule__Literal__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__2"


    // $ANTLR start "rule__Literal__Group__2__Impl"
    // InternalMORA.g:2558:1: rule__Literal__Group__2__Impl : ( ( rule__Literal__Group_2__0 )? ) ;
    public final void rule__Literal__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2562:1: ( ( ( rule__Literal__Group_2__0 )? ) )
            // InternalMORA.g:2563:1: ( ( rule__Literal__Group_2__0 )? )
            {
            // InternalMORA.g:2563:1: ( ( rule__Literal__Group_2__0 )? )
            // InternalMORA.g:2564:2: ( rule__Literal__Group_2__0 )?
            {
             before(grammarAccess.getLiteralAccess().getGroup_2()); 
            // InternalMORA.g:2565:2: ( rule__Literal__Group_2__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==30) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalMORA.g:2565:3: rule__Literal__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Literal__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLiteralAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group__2__Impl"


    // $ANTLR start "rule__Literal__Group_2__0"
    // InternalMORA.g:2574:1: rule__Literal__Group_2__0 : rule__Literal__Group_2__0__Impl rule__Literal__Group_2__1 ;
    public final void rule__Literal__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2578:1: ( rule__Literal__Group_2__0__Impl rule__Literal__Group_2__1 )
            // InternalMORA.g:2579:2: rule__Literal__Group_2__0__Impl rule__Literal__Group_2__1
            {
            pushFollow(FOLLOW_25);
            rule__Literal__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Literal__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_2__0"


    // $ANTLR start "rule__Literal__Group_2__0__Impl"
    // InternalMORA.g:2586:1: rule__Literal__Group_2__0__Impl : ( '=' ) ;
    public final void rule__Literal__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2590:1: ( ( '=' ) )
            // InternalMORA.g:2591:1: ( '=' )
            {
            // InternalMORA.g:2591:1: ( '=' )
            // InternalMORA.g:2592:2: '='
            {
             before(grammarAccess.getLiteralAccess().getEqualsSignKeyword_2_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getLiteralAccess().getEqualsSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_2__0__Impl"


    // $ANTLR start "rule__Literal__Group_2__1"
    // InternalMORA.g:2601:1: rule__Literal__Group_2__1 : rule__Literal__Group_2__1__Impl ;
    public final void rule__Literal__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2605:1: ( rule__Literal__Group_2__1__Impl )
            // InternalMORA.g:2606:2: rule__Literal__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Literal__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_2__1"


    // $ANTLR start "rule__Literal__Group_2__1__Impl"
    // InternalMORA.g:2612:1: rule__Literal__Group_2__1__Impl : ( ( rule__Literal__ValueAssignment_2_1 ) ) ;
    public final void rule__Literal__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2616:1: ( ( ( rule__Literal__ValueAssignment_2_1 ) ) )
            // InternalMORA.g:2617:1: ( ( rule__Literal__ValueAssignment_2_1 ) )
            {
            // InternalMORA.g:2617:1: ( ( rule__Literal__ValueAssignment_2_1 ) )
            // InternalMORA.g:2618:2: ( rule__Literal__ValueAssignment_2_1 )
            {
             before(grammarAccess.getLiteralAccess().getValueAssignment_2_1()); 
            // InternalMORA.g:2619:2: ( rule__Literal__ValueAssignment_2_1 )
            // InternalMORA.g:2619:3: rule__Literal__ValueAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Literal__ValueAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getLiteralAccess().getValueAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__Group_2__1__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__0"
    // InternalMORA.g:2628:1: rule__ListTypeDecl__Group__0 : rule__ListTypeDecl__Group__0__Impl rule__ListTypeDecl__Group__1 ;
    public final void rule__ListTypeDecl__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2632:1: ( rule__ListTypeDecl__Group__0__Impl rule__ListTypeDecl__Group__1 )
            // InternalMORA.g:2633:2: rule__ListTypeDecl__Group__0__Impl rule__ListTypeDecl__Group__1
            {
            pushFollow(FOLLOW_26);
            rule__ListTypeDecl__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__0"


    // $ANTLR start "rule__ListTypeDecl__Group__0__Impl"
    // InternalMORA.g:2640:1: rule__ListTypeDecl__Group__0__Impl : ( ( rule__ListTypeDecl__DocAssignment_0 )? ) ;
    public final void rule__ListTypeDecl__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2644:1: ( ( ( rule__ListTypeDecl__DocAssignment_0 )? ) )
            // InternalMORA.g:2645:1: ( ( rule__ListTypeDecl__DocAssignment_0 )? )
            {
            // InternalMORA.g:2645:1: ( ( rule__ListTypeDecl__DocAssignment_0 )? )
            // InternalMORA.g:2646:2: ( rule__ListTypeDecl__DocAssignment_0 )?
            {
             before(grammarAccess.getListTypeDeclAccess().getDocAssignment_0()); 
            // InternalMORA.g:2647:2: ( rule__ListTypeDecl__DocAssignment_0 )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_ML_COMMENT) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalMORA.g:2647:3: rule__ListTypeDecl__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ListTypeDecl__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getListTypeDeclAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__0__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__1"
    // InternalMORA.g:2655:1: rule__ListTypeDecl__Group__1 : rule__ListTypeDecl__Group__1__Impl rule__ListTypeDecl__Group__2 ;
    public final void rule__ListTypeDecl__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2659:1: ( rule__ListTypeDecl__Group__1__Impl rule__ListTypeDecl__Group__2 )
            // InternalMORA.g:2660:2: rule__ListTypeDecl__Group__1__Impl rule__ListTypeDecl__Group__2
            {
            pushFollow(FOLLOW_27);
            rule__ListTypeDecl__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__1"


    // $ANTLR start "rule__ListTypeDecl__Group__1__Impl"
    // InternalMORA.g:2667:1: rule__ListTypeDecl__Group__1__Impl : ( 'List' ) ;
    public final void rule__ListTypeDecl__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2671:1: ( ( 'List' ) )
            // InternalMORA.g:2672:1: ( 'List' )
            {
            // InternalMORA.g:2672:1: ( 'List' )
            // InternalMORA.g:2673:2: 'List'
            {
             before(grammarAccess.getListTypeDeclAccess().getListKeyword_1()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getListTypeDeclAccess().getListKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__1__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__2"
    // InternalMORA.g:2682:1: rule__ListTypeDecl__Group__2 : rule__ListTypeDecl__Group__2__Impl rule__ListTypeDecl__Group__3 ;
    public final void rule__ListTypeDecl__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2686:1: ( rule__ListTypeDecl__Group__2__Impl rule__ListTypeDecl__Group__3 )
            // InternalMORA.g:2687:2: rule__ListTypeDecl__Group__2__Impl rule__ListTypeDecl__Group__3
            {
            pushFollow(FOLLOW_28);
            rule__ListTypeDecl__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__2"


    // $ANTLR start "rule__ListTypeDecl__Group__2__Impl"
    // InternalMORA.g:2694:1: rule__ListTypeDecl__Group__2__Impl : ( '<' ) ;
    public final void rule__ListTypeDecl__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2698:1: ( ( '<' ) )
            // InternalMORA.g:2699:1: ( '<' )
            {
            // InternalMORA.g:2699:1: ( '<' )
            // InternalMORA.g:2700:2: '<'
            {
             before(grammarAccess.getListTypeDeclAccess().getLessThanSignKeyword_2()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getListTypeDeclAccess().getLessThanSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__2__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__3"
    // InternalMORA.g:2709:1: rule__ListTypeDecl__Group__3 : rule__ListTypeDecl__Group__3__Impl rule__ListTypeDecl__Group__4 ;
    public final void rule__ListTypeDecl__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2713:1: ( rule__ListTypeDecl__Group__3__Impl rule__ListTypeDecl__Group__4 )
            // InternalMORA.g:2714:2: rule__ListTypeDecl__Group__3__Impl rule__ListTypeDecl__Group__4
            {
            pushFollow(FOLLOW_29);
            rule__ListTypeDecl__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__3"


    // $ANTLR start "rule__ListTypeDecl__Group__3__Impl"
    // InternalMORA.g:2721:1: rule__ListTypeDecl__Group__3__Impl : ( ( rule__ListTypeDecl__Alternatives_3 ) ) ;
    public final void rule__ListTypeDecl__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2725:1: ( ( ( rule__ListTypeDecl__Alternatives_3 ) ) )
            // InternalMORA.g:2726:1: ( ( rule__ListTypeDecl__Alternatives_3 ) )
            {
            // InternalMORA.g:2726:1: ( ( rule__ListTypeDecl__Alternatives_3 ) )
            // InternalMORA.g:2727:2: ( rule__ListTypeDecl__Alternatives_3 )
            {
             before(grammarAccess.getListTypeDeclAccess().getAlternatives_3()); 
            // InternalMORA.g:2728:2: ( rule__ListTypeDecl__Alternatives_3 )
            // InternalMORA.g:2728:3: rule__ListTypeDecl__Alternatives_3
            {
            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Alternatives_3();

            state._fsp--;


            }

             after(grammarAccess.getListTypeDeclAccess().getAlternatives_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__3__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__4"
    // InternalMORA.g:2736:1: rule__ListTypeDecl__Group__4 : rule__ListTypeDecl__Group__4__Impl rule__ListTypeDecl__Group__5 ;
    public final void rule__ListTypeDecl__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2740:1: ( rule__ListTypeDecl__Group__4__Impl rule__ListTypeDecl__Group__5 )
            // InternalMORA.g:2741:2: rule__ListTypeDecl__Group__4__Impl rule__ListTypeDecl__Group__5
            {
            pushFollow(FOLLOW_5);
            rule__ListTypeDecl__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__4"


    // $ANTLR start "rule__ListTypeDecl__Group__4__Impl"
    // InternalMORA.g:2748:1: rule__ListTypeDecl__Group__4__Impl : ( '>' ) ;
    public final void rule__ListTypeDecl__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2752:1: ( ( '>' ) )
            // InternalMORA.g:2753:1: ( '>' )
            {
            // InternalMORA.g:2753:1: ( '>' )
            // InternalMORA.g:2754:2: '>'
            {
             before(grammarAccess.getListTypeDeclAccess().getGreaterThanSignKeyword_4()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getListTypeDeclAccess().getGreaterThanSignKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__4__Impl"


    // $ANTLR start "rule__ListTypeDecl__Group__5"
    // InternalMORA.g:2763:1: rule__ListTypeDecl__Group__5 : rule__ListTypeDecl__Group__5__Impl ;
    public final void rule__ListTypeDecl__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2767:1: ( rule__ListTypeDecl__Group__5__Impl )
            // InternalMORA.g:2768:2: rule__ListTypeDecl__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__5"


    // $ANTLR start "rule__ListTypeDecl__Group__5__Impl"
    // InternalMORA.g:2774:1: rule__ListTypeDecl__Group__5__Impl : ( ( rule__ListTypeDecl__NameAssignment_5 ) ) ;
    public final void rule__ListTypeDecl__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2778:1: ( ( ( rule__ListTypeDecl__NameAssignment_5 ) ) )
            // InternalMORA.g:2779:1: ( ( rule__ListTypeDecl__NameAssignment_5 ) )
            {
            // InternalMORA.g:2779:1: ( ( rule__ListTypeDecl__NameAssignment_5 ) )
            // InternalMORA.g:2780:2: ( rule__ListTypeDecl__NameAssignment_5 )
            {
             before(grammarAccess.getListTypeDeclAccess().getNameAssignment_5()); 
            // InternalMORA.g:2781:2: ( rule__ListTypeDecl__NameAssignment_5 )
            // InternalMORA.g:2781:3: rule__ListTypeDecl__NameAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ListTypeDecl__NameAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getListTypeDeclAccess().getNameAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__Group__5__Impl"


    // $ANTLR start "rule__Interface__Group__0"
    // InternalMORA.g:2790:1: rule__Interface__Group__0 : rule__Interface__Group__0__Impl rule__Interface__Group__1 ;
    public final void rule__Interface__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2794:1: ( rule__Interface__Group__0__Impl rule__Interface__Group__1 )
            // InternalMORA.g:2795:2: rule__Interface__Group__0__Impl rule__Interface__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__Interface__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__0"


    // $ANTLR start "rule__Interface__Group__0__Impl"
    // InternalMORA.g:2802:1: rule__Interface__Group__0__Impl : ( ( rule__Interface__DocAssignment_0 )? ) ;
    public final void rule__Interface__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2806:1: ( ( ( rule__Interface__DocAssignment_0 )? ) )
            // InternalMORA.g:2807:1: ( ( rule__Interface__DocAssignment_0 )? )
            {
            // InternalMORA.g:2807:1: ( ( rule__Interface__DocAssignment_0 )? )
            // InternalMORA.g:2808:2: ( rule__Interface__DocAssignment_0 )?
            {
             before(grammarAccess.getInterfaceAccess().getDocAssignment_0()); 
            // InternalMORA.g:2809:2: ( rule__Interface__DocAssignment_0 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ML_COMMENT) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalMORA.g:2809:3: rule__Interface__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Interface__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInterfaceAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__0__Impl"


    // $ANTLR start "rule__Interface__Group__1"
    // InternalMORA.g:2817:1: rule__Interface__Group__1 : rule__Interface__Group__1__Impl rule__Interface__Group__2 ;
    public final void rule__Interface__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2821:1: ( rule__Interface__Group__1__Impl rule__Interface__Group__2 )
            // InternalMORA.g:2822:2: rule__Interface__Group__1__Impl rule__Interface__Group__2
            {
            pushFollow(FOLLOW_30);
            rule__Interface__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__1"


    // $ANTLR start "rule__Interface__Group__1__Impl"
    // InternalMORA.g:2829:1: rule__Interface__Group__1__Impl : ( ( rule__Interface__AnnoAssignment_1 )* ) ;
    public final void rule__Interface__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2833:1: ( ( ( rule__Interface__AnnoAssignment_1 )* ) )
            // InternalMORA.g:2834:1: ( ( rule__Interface__AnnoAssignment_1 )* )
            {
            // InternalMORA.g:2834:1: ( ( rule__Interface__AnnoAssignment_1 )* )
            // InternalMORA.g:2835:2: ( rule__Interface__AnnoAssignment_1 )*
            {
             before(grammarAccess.getInterfaceAccess().getAnnoAssignment_1()); 
            // InternalMORA.g:2836:2: ( rule__Interface__AnnoAssignment_1 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==34) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalMORA.g:2836:3: rule__Interface__AnnoAssignment_1
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__Interface__AnnoAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getInterfaceAccess().getAnnoAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__1__Impl"


    // $ANTLR start "rule__Interface__Group__2"
    // InternalMORA.g:2844:1: rule__Interface__Group__2 : rule__Interface__Group__2__Impl rule__Interface__Group__3 ;
    public final void rule__Interface__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2848:1: ( rule__Interface__Group__2__Impl rule__Interface__Group__3 )
            // InternalMORA.g:2849:2: rule__Interface__Group__2__Impl rule__Interface__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Interface__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__2"


    // $ANTLR start "rule__Interface__Group__2__Impl"
    // InternalMORA.g:2856:1: rule__Interface__Group__2__Impl : ( 'interface' ) ;
    public final void rule__Interface__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2860:1: ( ( 'interface' ) )
            // InternalMORA.g:2861:1: ( 'interface' )
            {
            // InternalMORA.g:2861:1: ( 'interface' )
            // InternalMORA.g:2862:2: 'interface'
            {
             before(grammarAccess.getInterfaceAccess().getInterfaceKeyword_2()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getInterfaceKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__2__Impl"


    // $ANTLR start "rule__Interface__Group__3"
    // InternalMORA.g:2871:1: rule__Interface__Group__3 : rule__Interface__Group__3__Impl rule__Interface__Group__4 ;
    public final void rule__Interface__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2875:1: ( rule__Interface__Group__3__Impl rule__Interface__Group__4 )
            // InternalMORA.g:2876:2: rule__Interface__Group__3__Impl rule__Interface__Group__4
            {
            pushFollow(FOLLOW_31);
            rule__Interface__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__3"


    // $ANTLR start "rule__Interface__Group__3__Impl"
    // InternalMORA.g:2883:1: rule__Interface__Group__3__Impl : ( ( rule__Interface__NameAssignment_3 ) ) ;
    public final void rule__Interface__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2887:1: ( ( ( rule__Interface__NameAssignment_3 ) ) )
            // InternalMORA.g:2888:1: ( ( rule__Interface__NameAssignment_3 ) )
            {
            // InternalMORA.g:2888:1: ( ( rule__Interface__NameAssignment_3 ) )
            // InternalMORA.g:2889:2: ( rule__Interface__NameAssignment_3 )
            {
             before(grammarAccess.getInterfaceAccess().getNameAssignment_3()); 
            // InternalMORA.g:2890:2: ( rule__Interface__NameAssignment_3 )
            // InternalMORA.g:2890:3: rule__Interface__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Interface__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getInterfaceAccess().getNameAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__3__Impl"


    // $ANTLR start "rule__Interface__Group__4"
    // InternalMORA.g:2898:1: rule__Interface__Group__4 : rule__Interface__Group__4__Impl rule__Interface__Group__5 ;
    public final void rule__Interface__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2902:1: ( rule__Interface__Group__4__Impl rule__Interface__Group__5 )
            // InternalMORA.g:2903:2: rule__Interface__Group__4__Impl rule__Interface__Group__5
            {
            pushFollow(FOLLOW_31);
            rule__Interface__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__4"


    // $ANTLR start "rule__Interface__Group__4__Impl"
    // InternalMORA.g:2910:1: rule__Interface__Group__4__Impl : ( ( rule__Interface__Group_4__0 )? ) ;
    public final void rule__Interface__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2914:1: ( ( ( rule__Interface__Group_4__0 )? ) )
            // InternalMORA.g:2915:1: ( ( rule__Interface__Group_4__0 )? )
            {
            // InternalMORA.g:2915:1: ( ( rule__Interface__Group_4__0 )? )
            // InternalMORA.g:2916:2: ( rule__Interface__Group_4__0 )?
            {
             before(grammarAccess.getInterfaceAccess().getGroup_4()); 
            // InternalMORA.g:2917:2: ( rule__Interface__Group_4__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==41) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalMORA.g:2917:3: rule__Interface__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Interface__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getInterfaceAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__4__Impl"


    // $ANTLR start "rule__Interface__Group__5"
    // InternalMORA.g:2925:1: rule__Interface__Group__5 : rule__Interface__Group__5__Impl rule__Interface__Group__6 ;
    public final void rule__Interface__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2929:1: ( rule__Interface__Group__5__Impl rule__Interface__Group__6 )
            // InternalMORA.g:2930:2: rule__Interface__Group__5__Impl rule__Interface__Group__6
            {
            pushFollow(FOLLOW_32);
            rule__Interface__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__5"


    // $ANTLR start "rule__Interface__Group__5__Impl"
    // InternalMORA.g:2937:1: rule__Interface__Group__5__Impl : ( '{' ) ;
    public final void rule__Interface__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2941:1: ( ( '{' ) )
            // InternalMORA.g:2942:1: ( '{' )
            {
            // InternalMORA.g:2942:1: ( '{' )
            // InternalMORA.g:2943:2: '{'
            {
             before(grammarAccess.getInterfaceAccess().getLeftCurlyBracketKeyword_5()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getLeftCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__5__Impl"


    // $ANTLR start "rule__Interface__Group__6"
    // InternalMORA.g:2952:1: rule__Interface__Group__6 : rule__Interface__Group__6__Impl rule__Interface__Group__7 ;
    public final void rule__Interface__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2956:1: ( rule__Interface__Group__6__Impl rule__Interface__Group__7 )
            // InternalMORA.g:2957:2: rule__Interface__Group__6__Impl rule__Interface__Group__7
            {
            pushFollow(FOLLOW_32);
            rule__Interface__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__6"


    // $ANTLR start "rule__Interface__Group__6__Impl"
    // InternalMORA.g:2964:1: rule__Interface__Group__6__Impl : ( ( rule__Interface__Group_6__0 )* ) ;
    public final void rule__Interface__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2968:1: ( ( ( rule__Interface__Group_6__0 )* ) )
            // InternalMORA.g:2969:1: ( ( rule__Interface__Group_6__0 )* )
            {
            // InternalMORA.g:2969:1: ( ( rule__Interface__Group_6__0 )* )
            // InternalMORA.g:2970:2: ( rule__Interface__Group_6__0 )*
            {
             before(grammarAccess.getInterfaceAccess().getGroup_6()); 
            // InternalMORA.g:2971:2: ( rule__Interface__Group_6__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID||LA34_0==RULE_ML_COMMENT||(LA34_0>=13 && LA34_0<=21)) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalMORA.g:2971:3: rule__Interface__Group_6__0
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__Interface__Group_6__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getInterfaceAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__6__Impl"


    // $ANTLR start "rule__Interface__Group__7"
    // InternalMORA.g:2979:1: rule__Interface__Group__7 : rule__Interface__Group__7__Impl ;
    public final void rule__Interface__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2983:1: ( rule__Interface__Group__7__Impl )
            // InternalMORA.g:2984:2: rule__Interface__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Interface__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__7"


    // $ANTLR start "rule__Interface__Group__7__Impl"
    // InternalMORA.g:2990:1: rule__Interface__Group__7__Impl : ( '}' ) ;
    public final void rule__Interface__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:2994:1: ( ( '}' ) )
            // InternalMORA.g:2995:1: ( '}' )
            {
            // InternalMORA.g:2995:1: ( '}' )
            // InternalMORA.g:2996:2: '}'
            {
             before(grammarAccess.getInterfaceAccess().getRightCurlyBracketKeyword_7()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group__7__Impl"


    // $ANTLR start "rule__Interface__Group_4__0"
    // InternalMORA.g:3006:1: rule__Interface__Group_4__0 : rule__Interface__Group_4__0__Impl rule__Interface__Group_4__1 ;
    public final void rule__Interface__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3010:1: ( rule__Interface__Group_4__0__Impl rule__Interface__Group_4__1 )
            // InternalMORA.g:3011:2: rule__Interface__Group_4__0__Impl rule__Interface__Group_4__1
            {
            pushFollow(FOLLOW_5);
            rule__Interface__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__0"


    // $ANTLR start "rule__Interface__Group_4__0__Impl"
    // InternalMORA.g:3018:1: rule__Interface__Group_4__0__Impl : ( 'extends' ) ;
    public final void rule__Interface__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3022:1: ( ( 'extends' ) )
            // InternalMORA.g:3023:1: ( 'extends' )
            {
            // InternalMORA.g:3023:1: ( 'extends' )
            // InternalMORA.g:3024:2: 'extends'
            {
             before(grammarAccess.getInterfaceAccess().getExtendsKeyword_4_0()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getExtendsKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__0__Impl"


    // $ANTLR start "rule__Interface__Group_4__1"
    // InternalMORA.g:3033:1: rule__Interface__Group_4__1 : rule__Interface__Group_4__1__Impl rule__Interface__Group_4__2 ;
    public final void rule__Interface__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3037:1: ( rule__Interface__Group_4__1__Impl rule__Interface__Group_4__2 )
            // InternalMORA.g:3038:2: rule__Interface__Group_4__1__Impl rule__Interface__Group_4__2
            {
            pushFollow(FOLLOW_34);
            rule__Interface__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__1"


    // $ANTLR start "rule__Interface__Group_4__1__Impl"
    // InternalMORA.g:3045:1: rule__Interface__Group_4__1__Impl : ( ( rule__Interface__ParentsAssignment_4_1 ) ) ;
    public final void rule__Interface__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3049:1: ( ( ( rule__Interface__ParentsAssignment_4_1 ) ) )
            // InternalMORA.g:3050:1: ( ( rule__Interface__ParentsAssignment_4_1 ) )
            {
            // InternalMORA.g:3050:1: ( ( rule__Interface__ParentsAssignment_4_1 ) )
            // InternalMORA.g:3051:2: ( rule__Interface__ParentsAssignment_4_1 )
            {
             before(grammarAccess.getInterfaceAccess().getParentsAssignment_4_1()); 
            // InternalMORA.g:3052:2: ( rule__Interface__ParentsAssignment_4_1 )
            // InternalMORA.g:3052:3: rule__Interface__ParentsAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Interface__ParentsAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getInterfaceAccess().getParentsAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__1__Impl"


    // $ANTLR start "rule__Interface__Group_4__2"
    // InternalMORA.g:3060:1: rule__Interface__Group_4__2 : rule__Interface__Group_4__2__Impl ;
    public final void rule__Interface__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3064:1: ( rule__Interface__Group_4__2__Impl )
            // InternalMORA.g:3065:2: rule__Interface__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Interface__Group_4__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__2"


    // $ANTLR start "rule__Interface__Group_4__2__Impl"
    // InternalMORA.g:3071:1: rule__Interface__Group_4__2__Impl : ( ( rule__Interface__Group_4_2__0 )* ) ;
    public final void rule__Interface__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3075:1: ( ( ( rule__Interface__Group_4_2__0 )* ) )
            // InternalMORA.g:3076:1: ( ( rule__Interface__Group_4_2__0 )* )
            {
            // InternalMORA.g:3076:1: ( ( rule__Interface__Group_4_2__0 )* )
            // InternalMORA.g:3077:2: ( rule__Interface__Group_4_2__0 )*
            {
             before(grammarAccess.getInterfaceAccess().getGroup_4_2()); 
            // InternalMORA.g:3078:2: ( rule__Interface__Group_4_2__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==42) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalMORA.g:3078:3: rule__Interface__Group_4_2__0
            	    {
            	    pushFollow(FOLLOW_35);
            	    rule__Interface__Group_4_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getInterfaceAccess().getGroup_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4__2__Impl"


    // $ANTLR start "rule__Interface__Group_4_2__0"
    // InternalMORA.g:3087:1: rule__Interface__Group_4_2__0 : rule__Interface__Group_4_2__0__Impl rule__Interface__Group_4_2__1 ;
    public final void rule__Interface__Group_4_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3091:1: ( rule__Interface__Group_4_2__0__Impl rule__Interface__Group_4_2__1 )
            // InternalMORA.g:3092:2: rule__Interface__Group_4_2__0__Impl rule__Interface__Group_4_2__1
            {
            pushFollow(FOLLOW_5);
            rule__Interface__Group_4_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group_4_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4_2__0"


    // $ANTLR start "rule__Interface__Group_4_2__0__Impl"
    // InternalMORA.g:3099:1: rule__Interface__Group_4_2__0__Impl : ( ',' ) ;
    public final void rule__Interface__Group_4_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3103:1: ( ( ',' ) )
            // InternalMORA.g:3104:1: ( ',' )
            {
            // InternalMORA.g:3104:1: ( ',' )
            // InternalMORA.g:3105:2: ','
            {
             before(grammarAccess.getInterfaceAccess().getCommaKeyword_4_2_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getCommaKeyword_4_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4_2__0__Impl"


    // $ANTLR start "rule__Interface__Group_4_2__1"
    // InternalMORA.g:3114:1: rule__Interface__Group_4_2__1 : rule__Interface__Group_4_2__1__Impl ;
    public final void rule__Interface__Group_4_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3118:1: ( rule__Interface__Group_4_2__1__Impl )
            // InternalMORA.g:3119:2: rule__Interface__Group_4_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Interface__Group_4_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4_2__1"


    // $ANTLR start "rule__Interface__Group_4_2__1__Impl"
    // InternalMORA.g:3125:1: rule__Interface__Group_4_2__1__Impl : ( ( rule__Interface__ParentsAssignment_4_2_1 ) ) ;
    public final void rule__Interface__Group_4_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3129:1: ( ( ( rule__Interface__ParentsAssignment_4_2_1 ) ) )
            // InternalMORA.g:3130:1: ( ( rule__Interface__ParentsAssignment_4_2_1 ) )
            {
            // InternalMORA.g:3130:1: ( ( rule__Interface__ParentsAssignment_4_2_1 ) )
            // InternalMORA.g:3131:2: ( rule__Interface__ParentsAssignment_4_2_1 )
            {
             before(grammarAccess.getInterfaceAccess().getParentsAssignment_4_2_1()); 
            // InternalMORA.g:3132:2: ( rule__Interface__ParentsAssignment_4_2_1 )
            // InternalMORA.g:3132:3: rule__Interface__ParentsAssignment_4_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Interface__ParentsAssignment_4_2_1();

            state._fsp--;


            }

             after(grammarAccess.getInterfaceAccess().getParentsAssignment_4_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_4_2__1__Impl"


    // $ANTLR start "rule__Interface__Group_6__0"
    // InternalMORA.g:3141:1: rule__Interface__Group_6__0 : rule__Interface__Group_6__0__Impl rule__Interface__Group_6__1 ;
    public final void rule__Interface__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3145:1: ( rule__Interface__Group_6__0__Impl rule__Interface__Group_6__1 )
            // InternalMORA.g:3146:2: rule__Interface__Group_6__0__Impl rule__Interface__Group_6__1
            {
            pushFollow(FOLLOW_9);
            rule__Interface__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Interface__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_6__0"


    // $ANTLR start "rule__Interface__Group_6__0__Impl"
    // InternalMORA.g:3153:1: rule__Interface__Group_6__0__Impl : ( ( rule__Interface__MethodsAssignment_6_0 ) ) ;
    public final void rule__Interface__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3157:1: ( ( ( rule__Interface__MethodsAssignment_6_0 ) ) )
            // InternalMORA.g:3158:1: ( ( rule__Interface__MethodsAssignment_6_0 ) )
            {
            // InternalMORA.g:3158:1: ( ( rule__Interface__MethodsAssignment_6_0 ) )
            // InternalMORA.g:3159:2: ( rule__Interface__MethodsAssignment_6_0 )
            {
             before(grammarAccess.getInterfaceAccess().getMethodsAssignment_6_0()); 
            // InternalMORA.g:3160:2: ( rule__Interface__MethodsAssignment_6_0 )
            // InternalMORA.g:3160:3: rule__Interface__MethodsAssignment_6_0
            {
            pushFollow(FOLLOW_2);
            rule__Interface__MethodsAssignment_6_0();

            state._fsp--;


            }

             after(grammarAccess.getInterfaceAccess().getMethodsAssignment_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_6__0__Impl"


    // $ANTLR start "rule__Interface__Group_6__1"
    // InternalMORA.g:3168:1: rule__Interface__Group_6__1 : rule__Interface__Group_6__1__Impl ;
    public final void rule__Interface__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3172:1: ( rule__Interface__Group_6__1__Impl )
            // InternalMORA.g:3173:2: rule__Interface__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Interface__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_6__1"


    // $ANTLR start "rule__Interface__Group_6__1__Impl"
    // InternalMORA.g:3179:1: rule__Interface__Group_6__1__Impl : ( ( ';' )? ) ;
    public final void rule__Interface__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3183:1: ( ( ( ';' )? ) )
            // InternalMORA.g:3184:1: ( ( ';' )? )
            {
            // InternalMORA.g:3184:1: ( ( ';' )? )
            // InternalMORA.g:3185:2: ( ';' )?
            {
             before(grammarAccess.getInterfaceAccess().getSemicolonKeyword_6_1()); 
            // InternalMORA.g:3186:2: ( ';' )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==25) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalMORA.g:3186:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getInterfaceAccess().getSemicolonKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__Group_6__1__Impl"


    // $ANTLR start "rule__Method__Group__0"
    // InternalMORA.g:3195:1: rule__Method__Group__0 : rule__Method__Group__0__Impl rule__Method__Group__1 ;
    public final void rule__Method__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3199:1: ( rule__Method__Group__0__Impl rule__Method__Group__1 )
            // InternalMORA.g:3200:2: rule__Method__Group__0__Impl rule__Method__Group__1
            {
            pushFollow(FOLLOW_36);
            rule__Method__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__0"


    // $ANTLR start "rule__Method__Group__0__Impl"
    // InternalMORA.g:3207:1: rule__Method__Group__0__Impl : ( ( rule__Method__DocAssignment_0 )? ) ;
    public final void rule__Method__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3211:1: ( ( ( rule__Method__DocAssignment_0 )? ) )
            // InternalMORA.g:3212:1: ( ( rule__Method__DocAssignment_0 )? )
            {
            // InternalMORA.g:3212:1: ( ( rule__Method__DocAssignment_0 )? )
            // InternalMORA.g:3213:2: ( rule__Method__DocAssignment_0 )?
            {
             before(grammarAccess.getMethodAccess().getDocAssignment_0()); 
            // InternalMORA.g:3214:2: ( rule__Method__DocAssignment_0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==RULE_ML_COMMENT) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalMORA.g:3214:3: rule__Method__DocAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__DocAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMethodAccess().getDocAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__0__Impl"


    // $ANTLR start "rule__Method__Group__1"
    // InternalMORA.g:3222:1: rule__Method__Group__1 : rule__Method__Group__1__Impl rule__Method__Group__2 ;
    public final void rule__Method__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3226:1: ( rule__Method__Group__1__Impl rule__Method__Group__2 )
            // InternalMORA.g:3227:2: rule__Method__Group__1__Impl rule__Method__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Method__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__1"


    // $ANTLR start "rule__Method__Group__1__Impl"
    // InternalMORA.g:3234:1: rule__Method__Group__1__Impl : ( ( rule__Method__Alternatives_1 ) ) ;
    public final void rule__Method__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3238:1: ( ( ( rule__Method__Alternatives_1 ) ) )
            // InternalMORA.g:3239:1: ( ( rule__Method__Alternatives_1 ) )
            {
            // InternalMORA.g:3239:1: ( ( rule__Method__Alternatives_1 ) )
            // InternalMORA.g:3240:2: ( rule__Method__Alternatives_1 )
            {
             before(grammarAccess.getMethodAccess().getAlternatives_1()); 
            // InternalMORA.g:3241:2: ( rule__Method__Alternatives_1 )
            // InternalMORA.g:3241:3: rule__Method__Alternatives_1
            {
            pushFollow(FOLLOW_2);
            rule__Method__Alternatives_1();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getAlternatives_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__1__Impl"


    // $ANTLR start "rule__Method__Group__2"
    // InternalMORA.g:3249:1: rule__Method__Group__2 : rule__Method__Group__2__Impl rule__Method__Group__3 ;
    public final void rule__Method__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3253:1: ( rule__Method__Group__2__Impl rule__Method__Group__3 )
            // InternalMORA.g:3254:2: rule__Method__Group__2__Impl rule__Method__Group__3
            {
            pushFollow(FOLLOW_37);
            rule__Method__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__2"


    // $ANTLR start "rule__Method__Group__2__Impl"
    // InternalMORA.g:3261:1: rule__Method__Group__2__Impl : ( ( rule__Method__NameAssignment_2 ) ) ;
    public final void rule__Method__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3265:1: ( ( ( rule__Method__NameAssignment_2 ) ) )
            // InternalMORA.g:3266:1: ( ( rule__Method__NameAssignment_2 ) )
            {
            // InternalMORA.g:3266:1: ( ( rule__Method__NameAssignment_2 ) )
            // InternalMORA.g:3267:2: ( rule__Method__NameAssignment_2 )
            {
             before(grammarAccess.getMethodAccess().getNameAssignment_2()); 
            // InternalMORA.g:3268:2: ( rule__Method__NameAssignment_2 )
            // InternalMORA.g:3268:3: rule__Method__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Method__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__2__Impl"


    // $ANTLR start "rule__Method__Group__3"
    // InternalMORA.g:3276:1: rule__Method__Group__3 : rule__Method__Group__3__Impl rule__Method__Group__4 ;
    public final void rule__Method__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3280:1: ( rule__Method__Group__3__Impl rule__Method__Group__4 )
            // InternalMORA.g:3281:2: rule__Method__Group__3__Impl rule__Method__Group__4
            {
            pushFollow(FOLLOW_38);
            rule__Method__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__3"


    // $ANTLR start "rule__Method__Group__3__Impl"
    // InternalMORA.g:3288:1: rule__Method__Group__3__Impl : ( '(' ) ;
    public final void rule__Method__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3292:1: ( ( '(' ) )
            // InternalMORA.g:3293:1: ( '(' )
            {
            // InternalMORA.g:3293:1: ( '(' )
            // InternalMORA.g:3294:2: '('
            {
             before(grammarAccess.getMethodAccess().getLeftParenthesisKeyword_3()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__3__Impl"


    // $ANTLR start "rule__Method__Group__4"
    // InternalMORA.g:3303:1: rule__Method__Group__4 : rule__Method__Group__4__Impl rule__Method__Group__5 ;
    public final void rule__Method__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3307:1: ( rule__Method__Group__4__Impl rule__Method__Group__5 )
            // InternalMORA.g:3308:2: rule__Method__Group__4__Impl rule__Method__Group__5
            {
            pushFollow(FOLLOW_38);
            rule__Method__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__4"


    // $ANTLR start "rule__Method__Group__4__Impl"
    // InternalMORA.g:3315:1: rule__Method__Group__4__Impl : ( ( rule__Method__Group_4__0 )? ) ;
    public final void rule__Method__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3319:1: ( ( ( rule__Method__Group_4__0 )? ) )
            // InternalMORA.g:3320:1: ( ( rule__Method__Group_4__0 )? )
            {
            // InternalMORA.g:3320:1: ( ( rule__Method__Group_4__0 )? )
            // InternalMORA.g:3321:2: ( rule__Method__Group_4__0 )?
            {
             before(grammarAccess.getMethodAccess().getGroup_4()); 
            // InternalMORA.g:3322:2: ( rule__Method__Group_4__0 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_ID||(LA38_0>=13 && LA38_0<=21)) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalMORA.g:3322:3: rule__Method__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMethodAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__4__Impl"


    // $ANTLR start "rule__Method__Group__5"
    // InternalMORA.g:3330:1: rule__Method__Group__5 : rule__Method__Group__5__Impl rule__Method__Group__6 ;
    public final void rule__Method__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3334:1: ( rule__Method__Group__5__Impl rule__Method__Group__6 )
            // InternalMORA.g:3335:2: rule__Method__Group__5__Impl rule__Method__Group__6
            {
            pushFollow(FOLLOW_39);
            rule__Method__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__5"


    // $ANTLR start "rule__Method__Group__5__Impl"
    // InternalMORA.g:3342:1: rule__Method__Group__5__Impl : ( ')' ) ;
    public final void rule__Method__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3346:1: ( ( ')' ) )
            // InternalMORA.g:3347:1: ( ')' )
            {
            // InternalMORA.g:3347:1: ( ')' )
            // InternalMORA.g:3348:2: ')'
            {
             before(grammarAccess.getMethodAccess().getRightParenthesisKeyword_5()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__5__Impl"


    // $ANTLR start "rule__Method__Group__6"
    // InternalMORA.g:3357:1: rule__Method__Group__6 : rule__Method__Group__6__Impl ;
    public final void rule__Method__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3361:1: ( rule__Method__Group__6__Impl )
            // InternalMORA.g:3362:2: rule__Method__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__6"


    // $ANTLR start "rule__Method__Group__6__Impl"
    // InternalMORA.g:3368:1: rule__Method__Group__6__Impl : ( ( rule__Method__Group_6__0 )? ) ;
    public final void rule__Method__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3372:1: ( ( ( rule__Method__Group_6__0 )? ) )
            // InternalMORA.g:3373:1: ( ( rule__Method__Group_6__0 )? )
            {
            // InternalMORA.g:3373:1: ( ( rule__Method__Group_6__0 )? )
            // InternalMORA.g:3374:2: ( rule__Method__Group_6__0 )?
            {
             before(grammarAccess.getMethodAccess().getGroup_6()); 
            // InternalMORA.g:3375:2: ( rule__Method__Group_6__0 )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==46) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalMORA.g:3375:3: rule__Method__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Method__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMethodAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group__6__Impl"


    // $ANTLR start "rule__Method__Group_1_0__0"
    // InternalMORA.g:3384:1: rule__Method__Group_1_0__0 : rule__Method__Group_1_0__0__Impl rule__Method__Group_1_0__1 ;
    public final void rule__Method__Group_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3388:1: ( rule__Method__Group_1_0__0__Impl rule__Method__Group_1_0__1 )
            // InternalMORA.g:3389:2: rule__Method__Group_1_0__0__Impl rule__Method__Group_1_0__1
            {
            pushFollow(FOLLOW_40);
            rule__Method__Group_1_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_1_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_1_0__0"


    // $ANTLR start "rule__Method__Group_1_0__0__Impl"
    // InternalMORA.g:3396:1: rule__Method__Group_1_0__0__Impl : ( ( rule__Method__ReturnProxyTypeAssignment_1_0_0 ) ) ;
    public final void rule__Method__Group_1_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3400:1: ( ( ( rule__Method__ReturnProxyTypeAssignment_1_0_0 ) ) )
            // InternalMORA.g:3401:1: ( ( rule__Method__ReturnProxyTypeAssignment_1_0_0 ) )
            {
            // InternalMORA.g:3401:1: ( ( rule__Method__ReturnProxyTypeAssignment_1_0_0 ) )
            // InternalMORA.g:3402:2: ( rule__Method__ReturnProxyTypeAssignment_1_0_0 )
            {
             before(grammarAccess.getMethodAccess().getReturnProxyTypeAssignment_1_0_0()); 
            // InternalMORA.g:3403:2: ( rule__Method__ReturnProxyTypeAssignment_1_0_0 )
            // InternalMORA.g:3403:3: rule__Method__ReturnProxyTypeAssignment_1_0_0
            {
            pushFollow(FOLLOW_2);
            rule__Method__ReturnProxyTypeAssignment_1_0_0();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getReturnProxyTypeAssignment_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_1_0__0__Impl"


    // $ANTLR start "rule__Method__Group_1_0__1"
    // InternalMORA.g:3411:1: rule__Method__Group_1_0__1 : rule__Method__Group_1_0__1__Impl ;
    public final void rule__Method__Group_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3415:1: ( rule__Method__Group_1_0__1__Impl )
            // InternalMORA.g:3416:2: rule__Method__Group_1_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group_1_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_1_0__1"


    // $ANTLR start "rule__Method__Group_1_0__1__Impl"
    // InternalMORA.g:3422:1: rule__Method__Group_1_0__1__Impl : ( '*' ) ;
    public final void rule__Method__Group_1_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3426:1: ( ( '*' ) )
            // InternalMORA.g:3427:1: ( '*' )
            {
            // InternalMORA.g:3427:1: ( '*' )
            // InternalMORA.g:3428:2: '*'
            {
             before(grammarAccess.getMethodAccess().getAsteriskKeyword_1_0_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getAsteriskKeyword_1_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_1_0__1__Impl"


    // $ANTLR start "rule__Method__Group_4__0"
    // InternalMORA.g:3438:1: rule__Method__Group_4__0 : rule__Method__Group_4__0__Impl rule__Method__Group_4__1 ;
    public final void rule__Method__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3442:1: ( rule__Method__Group_4__0__Impl rule__Method__Group_4__1 )
            // InternalMORA.g:3443:2: rule__Method__Group_4__0__Impl rule__Method__Group_4__1
            {
            pushFollow(FOLLOW_34);
            rule__Method__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4__0"


    // $ANTLR start "rule__Method__Group_4__0__Impl"
    // InternalMORA.g:3450:1: rule__Method__Group_4__0__Impl : ( ( rule__Method__ParametersAssignment_4_0 ) ) ;
    public final void rule__Method__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3454:1: ( ( ( rule__Method__ParametersAssignment_4_0 ) ) )
            // InternalMORA.g:3455:1: ( ( rule__Method__ParametersAssignment_4_0 ) )
            {
            // InternalMORA.g:3455:1: ( ( rule__Method__ParametersAssignment_4_0 ) )
            // InternalMORA.g:3456:2: ( rule__Method__ParametersAssignment_4_0 )
            {
             before(grammarAccess.getMethodAccess().getParametersAssignment_4_0()); 
            // InternalMORA.g:3457:2: ( rule__Method__ParametersAssignment_4_0 )
            // InternalMORA.g:3457:3: rule__Method__ParametersAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__Method__ParametersAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getParametersAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4__0__Impl"


    // $ANTLR start "rule__Method__Group_4__1"
    // InternalMORA.g:3465:1: rule__Method__Group_4__1 : rule__Method__Group_4__1__Impl ;
    public final void rule__Method__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3469:1: ( rule__Method__Group_4__1__Impl )
            // InternalMORA.g:3470:2: rule__Method__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4__1"


    // $ANTLR start "rule__Method__Group_4__1__Impl"
    // InternalMORA.g:3476:1: rule__Method__Group_4__1__Impl : ( ( rule__Method__Group_4_1__0 )* ) ;
    public final void rule__Method__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3480:1: ( ( ( rule__Method__Group_4_1__0 )* ) )
            // InternalMORA.g:3481:1: ( ( rule__Method__Group_4_1__0 )* )
            {
            // InternalMORA.g:3481:1: ( ( rule__Method__Group_4_1__0 )* )
            // InternalMORA.g:3482:2: ( rule__Method__Group_4_1__0 )*
            {
             before(grammarAccess.getMethodAccess().getGroup_4_1()); 
            // InternalMORA.g:3483:2: ( rule__Method__Group_4_1__0 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==42) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalMORA.g:3483:3: rule__Method__Group_4_1__0
            	    {
            	    pushFollow(FOLLOW_35);
            	    rule__Method__Group_4_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

             after(grammarAccess.getMethodAccess().getGroup_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4__1__Impl"


    // $ANTLR start "rule__Method__Group_4_1__0"
    // InternalMORA.g:3492:1: rule__Method__Group_4_1__0 : rule__Method__Group_4_1__0__Impl rule__Method__Group_4_1__1 ;
    public final void rule__Method__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3496:1: ( rule__Method__Group_4_1__0__Impl rule__Method__Group_4_1__1 )
            // InternalMORA.g:3497:2: rule__Method__Group_4_1__0__Impl rule__Method__Group_4_1__1
            {
            pushFollow(FOLLOW_28);
            rule__Method__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_4_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4_1__0"


    // $ANTLR start "rule__Method__Group_4_1__0__Impl"
    // InternalMORA.g:3504:1: rule__Method__Group_4_1__0__Impl : ( ',' ) ;
    public final void rule__Method__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3508:1: ( ( ',' ) )
            // InternalMORA.g:3509:1: ( ',' )
            {
            // InternalMORA.g:3509:1: ( ',' )
            // InternalMORA.g:3510:2: ','
            {
             before(grammarAccess.getMethodAccess().getCommaKeyword_4_1_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getCommaKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4_1__0__Impl"


    // $ANTLR start "rule__Method__Group_4_1__1"
    // InternalMORA.g:3519:1: rule__Method__Group_4_1__1 : rule__Method__Group_4_1__1__Impl ;
    public final void rule__Method__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3523:1: ( rule__Method__Group_4_1__1__Impl )
            // InternalMORA.g:3524:2: rule__Method__Group_4_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group_4_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4_1__1"


    // $ANTLR start "rule__Method__Group_4_1__1__Impl"
    // InternalMORA.g:3530:1: rule__Method__Group_4_1__1__Impl : ( ( rule__Method__ParametersAssignment_4_1_1 ) ) ;
    public final void rule__Method__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3534:1: ( ( ( rule__Method__ParametersAssignment_4_1_1 ) ) )
            // InternalMORA.g:3535:1: ( ( rule__Method__ParametersAssignment_4_1_1 ) )
            {
            // InternalMORA.g:3535:1: ( ( rule__Method__ParametersAssignment_4_1_1 ) )
            // InternalMORA.g:3536:2: ( rule__Method__ParametersAssignment_4_1_1 )
            {
             before(grammarAccess.getMethodAccess().getParametersAssignment_4_1_1()); 
            // InternalMORA.g:3537:2: ( rule__Method__ParametersAssignment_4_1_1 )
            // InternalMORA.g:3537:3: rule__Method__ParametersAssignment_4_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Method__ParametersAssignment_4_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getParametersAssignment_4_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_4_1__1__Impl"


    // $ANTLR start "rule__Method__Group_6__0"
    // InternalMORA.g:3546:1: rule__Method__Group_6__0 : rule__Method__Group_6__0__Impl rule__Method__Group_6__1 ;
    public final void rule__Method__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3550:1: ( rule__Method__Group_6__0__Impl rule__Method__Group_6__1 )
            // InternalMORA.g:3551:2: rule__Method__Group_6__0__Impl rule__Method__Group_6__1
            {
            pushFollow(FOLLOW_41);
            rule__Method__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__0"


    // $ANTLR start "rule__Method__Group_6__0__Impl"
    // InternalMORA.g:3558:1: rule__Method__Group_6__0__Impl : ( 'throws' ) ;
    public final void rule__Method__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3562:1: ( ( 'throws' ) )
            // InternalMORA.g:3563:1: ( 'throws' )
            {
            // InternalMORA.g:3563:1: ( 'throws' )
            // InternalMORA.g:3564:2: 'throws'
            {
             before(grammarAccess.getMethodAccess().getThrowsKeyword_6_0()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getThrowsKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__0__Impl"


    // $ANTLR start "rule__Method__Group_6__1"
    // InternalMORA.g:3573:1: rule__Method__Group_6__1 : rule__Method__Group_6__1__Impl rule__Method__Group_6__2 ;
    public final void rule__Method__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3577:1: ( rule__Method__Group_6__1__Impl rule__Method__Group_6__2 )
            // InternalMORA.g:3578:2: rule__Method__Group_6__1__Impl rule__Method__Group_6__2
            {
            pushFollow(FOLLOW_34);
            rule__Method__Group_6__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_6__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__1"


    // $ANTLR start "rule__Method__Group_6__1__Impl"
    // InternalMORA.g:3585:1: rule__Method__Group_6__1__Impl : ( ( rule__Method__ExceptionsAssignment_6_1 ) ) ;
    public final void rule__Method__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3589:1: ( ( ( rule__Method__ExceptionsAssignment_6_1 ) ) )
            // InternalMORA.g:3590:1: ( ( rule__Method__ExceptionsAssignment_6_1 ) )
            {
            // InternalMORA.g:3590:1: ( ( rule__Method__ExceptionsAssignment_6_1 ) )
            // InternalMORA.g:3591:2: ( rule__Method__ExceptionsAssignment_6_1 )
            {
             before(grammarAccess.getMethodAccess().getExceptionsAssignment_6_1()); 
            // InternalMORA.g:3592:2: ( rule__Method__ExceptionsAssignment_6_1 )
            // InternalMORA.g:3592:3: rule__Method__ExceptionsAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__Method__ExceptionsAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getExceptionsAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__1__Impl"


    // $ANTLR start "rule__Method__Group_6__2"
    // InternalMORA.g:3600:1: rule__Method__Group_6__2 : rule__Method__Group_6__2__Impl ;
    public final void rule__Method__Group_6__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3604:1: ( rule__Method__Group_6__2__Impl )
            // InternalMORA.g:3605:2: rule__Method__Group_6__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group_6__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__2"


    // $ANTLR start "rule__Method__Group_6__2__Impl"
    // InternalMORA.g:3611:1: rule__Method__Group_6__2__Impl : ( ( rule__Method__Group_6_2__0 )* ) ;
    public final void rule__Method__Group_6__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3615:1: ( ( ( rule__Method__Group_6_2__0 )* ) )
            // InternalMORA.g:3616:1: ( ( rule__Method__Group_6_2__0 )* )
            {
            // InternalMORA.g:3616:1: ( ( rule__Method__Group_6_2__0 )* )
            // InternalMORA.g:3617:2: ( rule__Method__Group_6_2__0 )*
            {
             before(grammarAccess.getMethodAccess().getGroup_6_2()); 
            // InternalMORA.g:3618:2: ( rule__Method__Group_6_2__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==42) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalMORA.g:3618:3: rule__Method__Group_6_2__0
            	    {
            	    pushFollow(FOLLOW_35);
            	    rule__Method__Group_6_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

             after(grammarAccess.getMethodAccess().getGroup_6_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6__2__Impl"


    // $ANTLR start "rule__Method__Group_6_2__0"
    // InternalMORA.g:3627:1: rule__Method__Group_6_2__0 : rule__Method__Group_6_2__0__Impl rule__Method__Group_6_2__1 ;
    public final void rule__Method__Group_6_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3631:1: ( rule__Method__Group_6_2__0__Impl rule__Method__Group_6_2__1 )
            // InternalMORA.g:3632:2: rule__Method__Group_6_2__0__Impl rule__Method__Group_6_2__1
            {
            pushFollow(FOLLOW_41);
            rule__Method__Group_6_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Method__Group_6_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6_2__0"


    // $ANTLR start "rule__Method__Group_6_2__0__Impl"
    // InternalMORA.g:3639:1: rule__Method__Group_6_2__0__Impl : ( ',' ) ;
    public final void rule__Method__Group_6_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3643:1: ( ( ',' ) )
            // InternalMORA.g:3644:1: ( ',' )
            {
            // InternalMORA.g:3644:1: ( ',' )
            // InternalMORA.g:3645:2: ','
            {
             before(grammarAccess.getMethodAccess().getCommaKeyword_6_2_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getCommaKeyword_6_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6_2__0__Impl"


    // $ANTLR start "rule__Method__Group_6_2__1"
    // InternalMORA.g:3654:1: rule__Method__Group_6_2__1 : rule__Method__Group_6_2__1__Impl ;
    public final void rule__Method__Group_6_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3658:1: ( rule__Method__Group_6_2__1__Impl )
            // InternalMORA.g:3659:2: rule__Method__Group_6_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Method__Group_6_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6_2__1"


    // $ANTLR start "rule__Method__Group_6_2__1__Impl"
    // InternalMORA.g:3665:1: rule__Method__Group_6_2__1__Impl : ( ( rule__Method__ExceptionsAssignment_6_2_1 ) ) ;
    public final void rule__Method__Group_6_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3669:1: ( ( ( rule__Method__ExceptionsAssignment_6_2_1 ) ) )
            // InternalMORA.g:3670:1: ( ( rule__Method__ExceptionsAssignment_6_2_1 ) )
            {
            // InternalMORA.g:3670:1: ( ( rule__Method__ExceptionsAssignment_6_2_1 ) )
            // InternalMORA.g:3671:2: ( rule__Method__ExceptionsAssignment_6_2_1 )
            {
             before(grammarAccess.getMethodAccess().getExceptionsAssignment_6_2_1()); 
            // InternalMORA.g:3672:2: ( rule__Method__ExceptionsAssignment_6_2_1 )
            // InternalMORA.g:3672:3: rule__Method__ExceptionsAssignment_6_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Method__ExceptionsAssignment_6_2_1();

            state._fsp--;


            }

             after(grammarAccess.getMethodAccess().getExceptionsAssignment_6_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__Group_6_2__1__Impl"


    // $ANTLR start "rule__Exception__Group__0"
    // InternalMORA.g:3681:1: rule__Exception__Group__0 : rule__Exception__Group__0__Impl rule__Exception__Group__1 ;
    public final void rule__Exception__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3685:1: ( rule__Exception__Group__0__Impl rule__Exception__Group__1 )
            // InternalMORA.g:3686:2: rule__Exception__Group__0__Impl rule__Exception__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Exception__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Exception__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__0"


    // $ANTLR start "rule__Exception__Group__0__Impl"
    // InternalMORA.g:3693:1: rule__Exception__Group__0__Impl : ( 'exception' ) ;
    public final void rule__Exception__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3697:1: ( ( 'exception' ) )
            // InternalMORA.g:3698:1: ( 'exception' )
            {
            // InternalMORA.g:3698:1: ( 'exception' )
            // InternalMORA.g:3699:2: 'exception'
            {
             before(grammarAccess.getExceptionAccess().getExceptionKeyword_0()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getExceptionAccess().getExceptionKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__0__Impl"


    // $ANTLR start "rule__Exception__Group__1"
    // InternalMORA.g:3708:1: rule__Exception__Group__1 : rule__Exception__Group__1__Impl rule__Exception__Group__2 ;
    public final void rule__Exception__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3712:1: ( rule__Exception__Group__1__Impl rule__Exception__Group__2 )
            // InternalMORA.g:3713:2: rule__Exception__Group__1__Impl rule__Exception__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Exception__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Exception__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__1"


    // $ANTLR start "rule__Exception__Group__1__Impl"
    // InternalMORA.g:3720:1: rule__Exception__Group__1__Impl : ( ( rule__Exception__NameAssignment_1 ) ) ;
    public final void rule__Exception__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3724:1: ( ( ( rule__Exception__NameAssignment_1 ) ) )
            // InternalMORA.g:3725:1: ( ( rule__Exception__NameAssignment_1 ) )
            {
            // InternalMORA.g:3725:1: ( ( rule__Exception__NameAssignment_1 ) )
            // InternalMORA.g:3726:2: ( rule__Exception__NameAssignment_1 )
            {
             before(grammarAccess.getExceptionAccess().getNameAssignment_1()); 
            // InternalMORA.g:3727:2: ( rule__Exception__NameAssignment_1 )
            // InternalMORA.g:3727:3: rule__Exception__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Exception__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExceptionAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__1__Impl"


    // $ANTLR start "rule__Exception__Group__2"
    // InternalMORA.g:3735:1: rule__Exception__Group__2 : rule__Exception__Group__2__Impl rule__Exception__Group__3 ;
    public final void rule__Exception__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3739:1: ( rule__Exception__Group__2__Impl rule__Exception__Group__3 )
            // InternalMORA.g:3740:2: rule__Exception__Group__2__Impl rule__Exception__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__Exception__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Exception__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__2"


    // $ANTLR start "rule__Exception__Group__2__Impl"
    // InternalMORA.g:3747:1: rule__Exception__Group__2__Impl : ( '{' ) ;
    public final void rule__Exception__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3751:1: ( ( '{' ) )
            // InternalMORA.g:3752:1: ( '{' )
            {
            // InternalMORA.g:3752:1: ( '{' )
            // InternalMORA.g:3753:2: '{'
            {
             before(grammarAccess.getExceptionAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getExceptionAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__2__Impl"


    // $ANTLR start "rule__Exception__Group__3"
    // InternalMORA.g:3762:1: rule__Exception__Group__3 : rule__Exception__Group__3__Impl rule__Exception__Group__4 ;
    public final void rule__Exception__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3766:1: ( rule__Exception__Group__3__Impl rule__Exception__Group__4 )
            // InternalMORA.g:3767:2: rule__Exception__Group__3__Impl rule__Exception__Group__4
            {
            pushFollow(FOLLOW_19);
            rule__Exception__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Exception__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__3"


    // $ANTLR start "rule__Exception__Group__3__Impl"
    // InternalMORA.g:3774:1: rule__Exception__Group__3__Impl : ( ( rule__Exception__Group_3__0 )* ) ;
    public final void rule__Exception__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3778:1: ( ( ( rule__Exception__Group_3__0 )* ) )
            // InternalMORA.g:3779:1: ( ( rule__Exception__Group_3__0 )* )
            {
            // InternalMORA.g:3779:1: ( ( rule__Exception__Group_3__0 )* )
            // InternalMORA.g:3780:2: ( rule__Exception__Group_3__0 )*
            {
             before(grammarAccess.getExceptionAccess().getGroup_3()); 
            // InternalMORA.g:3781:2: ( rule__Exception__Group_3__0 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID||LA42_0==RULE_ML_COMMENT||(LA42_0>=13 && LA42_0<=21)||LA42_0==34) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalMORA.g:3781:3: rule__Exception__Group_3__0
            	    {
            	    pushFollow(FOLLOW_20);
            	    rule__Exception__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

             after(grammarAccess.getExceptionAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__3__Impl"


    // $ANTLR start "rule__Exception__Group__4"
    // InternalMORA.g:3789:1: rule__Exception__Group__4 : rule__Exception__Group__4__Impl ;
    public final void rule__Exception__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3793:1: ( rule__Exception__Group__4__Impl )
            // InternalMORA.g:3794:2: rule__Exception__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Exception__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__4"


    // $ANTLR start "rule__Exception__Group__4__Impl"
    // InternalMORA.g:3800:1: rule__Exception__Group__4__Impl : ( '}' ) ;
    public final void rule__Exception__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3804:1: ( ( '}' ) )
            // InternalMORA.g:3805:1: ( '}' )
            {
            // InternalMORA.g:3805:1: ( '}' )
            // InternalMORA.g:3806:2: '}'
            {
             before(grammarAccess.getExceptionAccess().getRightCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getExceptionAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group__4__Impl"


    // $ANTLR start "rule__Exception__Group_3__0"
    // InternalMORA.g:3816:1: rule__Exception__Group_3__0 : rule__Exception__Group_3__0__Impl rule__Exception__Group_3__1 ;
    public final void rule__Exception__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3820:1: ( rule__Exception__Group_3__0__Impl rule__Exception__Group_3__1 )
            // InternalMORA.g:3821:2: rule__Exception__Group_3__0__Impl rule__Exception__Group_3__1
            {
            pushFollow(FOLLOW_9);
            rule__Exception__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Exception__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group_3__0"


    // $ANTLR start "rule__Exception__Group_3__0__Impl"
    // InternalMORA.g:3828:1: rule__Exception__Group_3__0__Impl : ( ( rule__Exception__MemberAssignment_3_0 ) ) ;
    public final void rule__Exception__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3832:1: ( ( ( rule__Exception__MemberAssignment_3_0 ) ) )
            // InternalMORA.g:3833:1: ( ( rule__Exception__MemberAssignment_3_0 ) )
            {
            // InternalMORA.g:3833:1: ( ( rule__Exception__MemberAssignment_3_0 ) )
            // InternalMORA.g:3834:2: ( rule__Exception__MemberAssignment_3_0 )
            {
             before(grammarAccess.getExceptionAccess().getMemberAssignment_3_0()); 
            // InternalMORA.g:3835:2: ( rule__Exception__MemberAssignment_3_0 )
            // InternalMORA.g:3835:3: rule__Exception__MemberAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Exception__MemberAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getExceptionAccess().getMemberAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group_3__0__Impl"


    // $ANTLR start "rule__Exception__Group_3__1"
    // InternalMORA.g:3843:1: rule__Exception__Group_3__1 : rule__Exception__Group_3__1__Impl ;
    public final void rule__Exception__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3847:1: ( rule__Exception__Group_3__1__Impl )
            // InternalMORA.g:3848:2: rule__Exception__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Exception__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group_3__1"


    // $ANTLR start "rule__Exception__Group_3__1__Impl"
    // InternalMORA.g:3854:1: rule__Exception__Group_3__1__Impl : ( ( ';' )? ) ;
    public final void rule__Exception__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3858:1: ( ( ( ';' )? ) )
            // InternalMORA.g:3859:1: ( ( ';' )? )
            {
            // InternalMORA.g:3859:1: ( ( ';' )? )
            // InternalMORA.g:3860:2: ( ';' )?
            {
             before(grammarAccess.getExceptionAccess().getSemicolonKeyword_3_1()); 
            // InternalMORA.g:3861:2: ( ';' )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==25) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalMORA.g:3861:3: ';'
                    {
                    match(input,25,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getExceptionAccess().getSemicolonKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__Group_3__1__Impl"


    // $ANTLR start "rule__Parameter__Group__0"
    // InternalMORA.g:3870:1: rule__Parameter__Group__0 : rule__Parameter__Group__0__Impl rule__Parameter__Group__1 ;
    public final void rule__Parameter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3874:1: ( rule__Parameter__Group__0__Impl rule__Parameter__Group__1 )
            // InternalMORA.g:3875:2: rule__Parameter__Group__0__Impl rule__Parameter__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Parameter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parameter__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__0"


    // $ANTLR start "rule__Parameter__Group__0__Impl"
    // InternalMORA.g:3882:1: rule__Parameter__Group__0__Impl : ( ( rule__Parameter__Alternatives_0 ) ) ;
    public final void rule__Parameter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3886:1: ( ( ( rule__Parameter__Alternatives_0 ) ) )
            // InternalMORA.g:3887:1: ( ( rule__Parameter__Alternatives_0 ) )
            {
            // InternalMORA.g:3887:1: ( ( rule__Parameter__Alternatives_0 ) )
            // InternalMORA.g:3888:2: ( rule__Parameter__Alternatives_0 )
            {
             before(grammarAccess.getParameterAccess().getAlternatives_0()); 
            // InternalMORA.g:3889:2: ( rule__Parameter__Alternatives_0 )
            // InternalMORA.g:3889:3: rule__Parameter__Alternatives_0
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Alternatives_0();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getAlternatives_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__0__Impl"


    // $ANTLR start "rule__Parameter__Group__1"
    // InternalMORA.g:3897:1: rule__Parameter__Group__1 : rule__Parameter__Group__1__Impl ;
    public final void rule__Parameter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3901:1: ( rule__Parameter__Group__1__Impl )
            // InternalMORA.g:3902:2: rule__Parameter__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__1"


    // $ANTLR start "rule__Parameter__Group__1__Impl"
    // InternalMORA.g:3908:1: rule__Parameter__Group__1__Impl : ( ( rule__Parameter__NameAssignment_1 ) ) ;
    public final void rule__Parameter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3912:1: ( ( ( rule__Parameter__NameAssignment_1 ) ) )
            // InternalMORA.g:3913:1: ( ( rule__Parameter__NameAssignment_1 ) )
            {
            // InternalMORA.g:3913:1: ( ( rule__Parameter__NameAssignment_1 ) )
            // InternalMORA.g:3914:2: ( rule__Parameter__NameAssignment_1 )
            {
             before(grammarAccess.getParameterAccess().getNameAssignment_1()); 
            // InternalMORA.g:3915:2: ( rule__Parameter__NameAssignment_1 )
            // InternalMORA.g:3915:3: rule__Parameter__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group__1__Impl"


    // $ANTLR start "rule__Parameter__Group_0_2__0"
    // InternalMORA.g:3924:1: rule__Parameter__Group_0_2__0 : rule__Parameter__Group_0_2__0__Impl rule__Parameter__Group_0_2__1 ;
    public final void rule__Parameter__Group_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3928:1: ( rule__Parameter__Group_0_2__0__Impl rule__Parameter__Group_0_2__1 )
            // InternalMORA.g:3929:2: rule__Parameter__Group_0_2__0__Impl rule__Parameter__Group_0_2__1
            {
            pushFollow(FOLLOW_40);
            rule__Parameter__Group_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Parameter__Group_0_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group_0_2__0"


    // $ANTLR start "rule__Parameter__Group_0_2__0__Impl"
    // InternalMORA.g:3936:1: rule__Parameter__Group_0_2__0__Impl : ( ( rule__Parameter__ProxyTypeAssignment_0_2_0 ) ) ;
    public final void rule__Parameter__Group_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3940:1: ( ( ( rule__Parameter__ProxyTypeAssignment_0_2_0 ) ) )
            // InternalMORA.g:3941:1: ( ( rule__Parameter__ProxyTypeAssignment_0_2_0 ) )
            {
            // InternalMORA.g:3941:1: ( ( rule__Parameter__ProxyTypeAssignment_0_2_0 ) )
            // InternalMORA.g:3942:2: ( rule__Parameter__ProxyTypeAssignment_0_2_0 )
            {
             before(grammarAccess.getParameterAccess().getProxyTypeAssignment_0_2_0()); 
            // InternalMORA.g:3943:2: ( rule__Parameter__ProxyTypeAssignment_0_2_0 )
            // InternalMORA.g:3943:3: rule__Parameter__ProxyTypeAssignment_0_2_0
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__ProxyTypeAssignment_0_2_0();

            state._fsp--;


            }

             after(grammarAccess.getParameterAccess().getProxyTypeAssignment_0_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group_0_2__0__Impl"


    // $ANTLR start "rule__Parameter__Group_0_2__1"
    // InternalMORA.g:3951:1: rule__Parameter__Group_0_2__1 : rule__Parameter__Group_0_2__1__Impl ;
    public final void rule__Parameter__Group_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3955:1: ( rule__Parameter__Group_0_2__1__Impl )
            // InternalMORA.g:3956:2: rule__Parameter__Group_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Parameter__Group_0_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group_0_2__1"


    // $ANTLR start "rule__Parameter__Group_0_2__1__Impl"
    // InternalMORA.g:3962:1: rule__Parameter__Group_0_2__1__Impl : ( '*' ) ;
    public final void rule__Parameter__Group_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3966:1: ( ( '*' ) )
            // InternalMORA.g:3967:1: ( '*' )
            {
            // InternalMORA.g:3967:1: ( '*' )
            // InternalMORA.g:3968:2: '*'
            {
             before(grammarAccess.getParameterAccess().getAsteriskKeyword_0_2_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getAsteriskKeyword_0_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__Group_0_2__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group__0"
    // InternalMORA.g:3978:1: rule__QualifiedName__Group__0 : rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 ;
    public final void rule__QualifiedName__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3982:1: ( rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1 )
            // InternalMORA.g:3983:2: rule__QualifiedName__Group__0__Impl rule__QualifiedName__Group__1
            {
            pushFollow(FOLLOW_42);
            rule__QualifiedName__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0"


    // $ANTLR start "rule__QualifiedName__Group__0__Impl"
    // InternalMORA.g:3990:1: rule__QualifiedName__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:3994:1: ( ( RULE_ID ) )
            // InternalMORA.g:3995:1: ( RULE_ID )
            {
            // InternalMORA.g:3995:1: ( RULE_ID )
            // InternalMORA.g:3996:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group__1"
    // InternalMORA.g:4005:1: rule__QualifiedName__Group__1 : rule__QualifiedName__Group__1__Impl ;
    public final void rule__QualifiedName__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4009:1: ( rule__QualifiedName__Group__1__Impl )
            // InternalMORA.g:4010:2: rule__QualifiedName__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1"


    // $ANTLR start "rule__QualifiedName__Group__1__Impl"
    // InternalMORA.g:4016:1: rule__QualifiedName__Group__1__Impl : ( ( rule__QualifiedName__Group_1__0 )* ) ;
    public final void rule__QualifiedName__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4020:1: ( ( ( rule__QualifiedName__Group_1__0 )* ) )
            // InternalMORA.g:4021:1: ( ( rule__QualifiedName__Group_1__0 )* )
            {
            // InternalMORA.g:4021:1: ( ( rule__QualifiedName__Group_1__0 )* )
            // InternalMORA.g:4022:2: ( rule__QualifiedName__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedNameAccess().getGroup_1()); 
            // InternalMORA.g:4023:2: ( rule__QualifiedName__Group_1__0 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>=11 && LA44_0<=12)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalMORA.g:4023:3: rule__QualifiedName__Group_1__0
            	    {
            	    pushFollow(FOLLOW_43);
            	    rule__QualifiedName__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

             after(grammarAccess.getQualifiedNameAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group__1__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__0"
    // InternalMORA.g:4032:1: rule__QualifiedName__Group_1__0 : rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 ;
    public final void rule__QualifiedName__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4036:1: ( rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1 )
            // InternalMORA.g:4037:2: rule__QualifiedName__Group_1__0__Impl rule__QualifiedName__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__QualifiedName__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0"


    // $ANTLR start "rule__QualifiedName__Group_1__0__Impl"
    // InternalMORA.g:4044:1: rule__QualifiedName__Group_1__0__Impl : ( ( rule__QualifiedName__Alternatives_1_0 ) ) ;
    public final void rule__QualifiedName__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4048:1: ( ( ( rule__QualifiedName__Alternatives_1_0 ) ) )
            // InternalMORA.g:4049:1: ( ( rule__QualifiedName__Alternatives_1_0 ) )
            {
            // InternalMORA.g:4049:1: ( ( rule__QualifiedName__Alternatives_1_0 ) )
            // InternalMORA.g:4050:2: ( rule__QualifiedName__Alternatives_1_0 )
            {
             before(grammarAccess.getQualifiedNameAccess().getAlternatives_1_0()); 
            // InternalMORA.g:4051:2: ( rule__QualifiedName__Alternatives_1_0 )
            // InternalMORA.g:4051:3: rule__QualifiedName__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Alternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedNameAccess().getAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedName__Group_1__1"
    // InternalMORA.g:4059:1: rule__QualifiedName__Group_1__1 : rule__QualifiedName__Group_1__1__Impl ;
    public final void rule__QualifiedName__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4063:1: ( rule__QualifiedName__Group_1__1__Impl )
            // InternalMORA.g:4064:2: rule__QualifiedName__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedName__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1"


    // $ANTLR start "rule__QualifiedName__Group_1__1__Impl"
    // InternalMORA.g:4070:1: rule__QualifiedName__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedName__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4074:1: ( ( RULE_ID ) )
            // InternalMORA.g:4075:1: ( RULE_ID )
            {
            // InternalMORA.g:4075:1: ( RULE_ID )
            // InternalMORA.g:4076:2: RULE_ID
            {
             before(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedName__Group_1__1__Impl"


    // $ANTLR start "rule__Model__IncludesAssignment_0"
    // InternalMORA.g:4086:1: rule__Model__IncludesAssignment_0 : ( ruleInclude ) ;
    public final void rule__Model__IncludesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4090:1: ( ( ruleInclude ) )
            // InternalMORA.g:4091:2: ( ruleInclude )
            {
            // InternalMORA.g:4091:2: ( ruleInclude )
            // InternalMORA.g:4092:3: ruleInclude
            {
             before(grammarAccess.getModelAccess().getIncludesIncludeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleInclude();

            state._fsp--;

             after(grammarAccess.getModelAccess().getIncludesIncludeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__IncludesAssignment_0"


    // $ANTLR start "rule__Model__OptionsAssignment_1"
    // InternalMORA.g:4101:1: rule__Model__OptionsAssignment_1 : ( ruleOptions ) ;
    public final void rule__Model__OptionsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4105:1: ( ( ruleOptions ) )
            // InternalMORA.g:4106:2: ( ruleOptions )
            {
            // InternalMORA.g:4106:2: ( ruleOptions )
            // InternalMORA.g:4107:3: ruleOptions
            {
             before(grammarAccess.getModelAccess().getOptionsOptionsParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleOptions();

            state._fsp--;

             after(grammarAccess.getModelAccess().getOptionsOptionsParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__OptionsAssignment_1"


    // $ANTLR start "rule__Model__NameAssignment_3"
    // InternalMORA.g:4116:1: rule__Model__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Model__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4120:1: ( ( RULE_ID ) )
            // InternalMORA.g:4121:2: ( RULE_ID )
            {
            // InternalMORA.g:4121:2: ( RULE_ID )
            // InternalMORA.g:4122:3: RULE_ID
            {
             before(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__NameAssignment_3"


    // $ANTLR start "rule__Model__InterfacesAssignment_5_0_0"
    // InternalMORA.g:4131:1: rule__Model__InterfacesAssignment_5_0_0 : ( ruleInterface ) ;
    public final void rule__Model__InterfacesAssignment_5_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4135:1: ( ( ruleInterface ) )
            // InternalMORA.g:4136:2: ( ruleInterface )
            {
            // InternalMORA.g:4136:2: ( ruleInterface )
            // InternalMORA.g:4137:3: ruleInterface
            {
             before(grammarAccess.getModelAccess().getInterfacesInterfaceParserRuleCall_5_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleInterface();

            state._fsp--;

             after(grammarAccess.getModelAccess().getInterfacesInterfaceParserRuleCall_5_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__InterfacesAssignment_5_0_0"


    // $ANTLR start "rule__Model__TypesAssignment_5_0_1"
    // InternalMORA.g:4146:1: rule__Model__TypesAssignment_5_0_1 : ( ruleTypeDecl ) ;
    public final void rule__Model__TypesAssignment_5_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4150:1: ( ( ruleTypeDecl ) )
            // InternalMORA.g:4151:2: ( ruleTypeDecl )
            {
            // InternalMORA.g:4151:2: ( ruleTypeDecl )
            // InternalMORA.g:4152:3: ruleTypeDecl
            {
             before(grammarAccess.getModelAccess().getTypesTypeDeclParserRuleCall_5_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleTypeDecl();

            state._fsp--;

             after(grammarAccess.getModelAccess().getTypesTypeDeclParserRuleCall_5_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__TypesAssignment_5_0_1"


    // $ANTLR start "rule__Include__ImportUriAssignment_1"
    // InternalMORA.g:4161:1: rule__Include__ImportUriAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Include__ImportUriAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4165:1: ( ( RULE_STRING ) )
            // InternalMORA.g:4166:2: ( RULE_STRING )
            {
            // InternalMORA.g:4166:2: ( RULE_STRING )
            // InternalMORA.g:4167:3: RULE_STRING
            {
             before(grammarAccess.getIncludeAccess().getImportUriSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getIncludeAccess().getImportUriSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Include__ImportUriAssignment_1"


    // $ANTLR start "rule__Options__JavaOptionsAssignment_2_0"
    // InternalMORA.g:4176:1: rule__Options__JavaOptionsAssignment_2_0 : ( ruleJavaOptions ) ;
    public final void rule__Options__JavaOptionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4180:1: ( ( ruleJavaOptions ) )
            // InternalMORA.g:4181:2: ( ruleJavaOptions )
            {
            // InternalMORA.g:4181:2: ( ruleJavaOptions )
            // InternalMORA.g:4182:3: ruleJavaOptions
            {
             before(grammarAccess.getOptionsAccess().getJavaOptionsJavaOptionsParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleJavaOptions();

            state._fsp--;

             after(grammarAccess.getOptionsAccess().getJavaOptionsJavaOptionsParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__JavaOptionsAssignment_2_0"


    // $ANTLR start "rule__Options__CsOptionsAssignment_2_1"
    // InternalMORA.g:4191:1: rule__Options__CsOptionsAssignment_2_1 : ( ruleCSharpOptions ) ;
    public final void rule__Options__CsOptionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4195:1: ( ( ruleCSharpOptions ) )
            // InternalMORA.g:4196:2: ( ruleCSharpOptions )
            {
            // InternalMORA.g:4196:2: ( ruleCSharpOptions )
            // InternalMORA.g:4197:3: ruleCSharpOptions
            {
             before(grammarAccess.getOptionsAccess().getCsOptionsCSharpOptionsParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCSharpOptions();

            state._fsp--;

             after(grammarAccess.getOptionsAccess().getCsOptionsCSharpOptionsParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__CsOptionsAssignment_2_1"


    // $ANTLR start "rule__Options__CppOptionsAssignment_2_2"
    // InternalMORA.g:4206:1: rule__Options__CppOptionsAssignment_2_2 : ( ruleCppOptions ) ;
    public final void rule__Options__CppOptionsAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4210:1: ( ( ruleCppOptions ) )
            // InternalMORA.g:4211:2: ( ruleCppOptions )
            {
            // InternalMORA.g:4211:2: ( ruleCppOptions )
            // InternalMORA.g:4212:3: ruleCppOptions
            {
             before(grammarAccess.getOptionsAccess().getCppOptionsCppOptionsParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCppOptions();

            state._fsp--;

             after(grammarAccess.getOptionsAccess().getCppOptionsCppOptionsParserRuleCall_2_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Options__CppOptionsAssignment_2_2"


    // $ANTLR start "rule__JavaOptions__BasePackageAssignment_4"
    // InternalMORA.g:4221:1: rule__JavaOptions__BasePackageAssignment_4 : ( ruleQualifiedName ) ;
    public final void rule__JavaOptions__BasePackageAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4225:1: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4226:2: ( ruleQualifiedName )
            {
            // InternalMORA.g:4226:2: ( ruleQualifiedName )
            // InternalMORA.g:4227:3: ruleQualifiedName
            {
             before(grammarAccess.getJavaOptionsAccess().getBasePackageQualifiedNameParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getJavaOptionsAccess().getBasePackageQualifiedNameParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__JavaOptions__BasePackageAssignment_4"


    // $ANTLR start "rule__CSharpOptions__BaseNamespaceAssignment_4"
    // InternalMORA.g:4236:1: rule__CSharpOptions__BaseNamespaceAssignment_4 : ( ruleQualifiedName ) ;
    public final void rule__CSharpOptions__BaseNamespaceAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4240:1: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4241:2: ( ruleQualifiedName )
            {
            // InternalMORA.g:4241:2: ( ruleQualifiedName )
            // InternalMORA.g:4242:3: ruleQualifiedName
            {
             before(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CSharpOptions__BaseNamespaceAssignment_4"


    // $ANTLR start "rule__CppOptions__BaseNamespaceAssignment_4"
    // InternalMORA.g:4251:1: rule__CppOptions__BaseNamespaceAssignment_4 : ( ruleQualifiedName ) ;
    public final void rule__CppOptions__BaseNamespaceAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4255:1: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4256:2: ( ruleQualifiedName )
            {
            // InternalMORA.g:4256:2: ( ruleQualifiedName )
            // InternalMORA.g:4257:3: ruleQualifiedName
            {
             before(grammarAccess.getCppOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getCppOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CppOptions__BaseNamespaceAssignment_4"


    // $ANTLR start "rule__PrimTypeDecl__NameAssignment"
    // InternalMORA.g:4266:1: rule__PrimTypeDecl__NameAssignment : ( rulePrimTypeLiteral ) ;
    public final void rule__PrimTypeDecl__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4270:1: ( ( rulePrimTypeLiteral ) )
            // InternalMORA.g:4271:2: ( rulePrimTypeLiteral )
            {
            // InternalMORA.g:4271:2: ( rulePrimTypeLiteral )
            // InternalMORA.g:4272:3: rulePrimTypeLiteral
            {
             before(grammarAccess.getPrimTypeDeclAccess().getNamePrimTypeLiteralEnumRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePrimTypeLiteral();

            state._fsp--;

             after(grammarAccess.getPrimTypeDeclAccess().getNamePrimTypeLiteralEnumRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PrimTypeDecl__NameAssignment"


    // $ANTLR start "rule__Annotation__NameAssignment_1"
    // InternalMORA.g:4281:1: rule__Annotation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Annotation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4285:1: ( ( RULE_ID ) )
            // InternalMORA.g:4286:2: ( RULE_ID )
            {
            // InternalMORA.g:4286:2: ( RULE_ID )
            // InternalMORA.g:4287:3: RULE_ID
            {
             before(grammarAccess.getAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAnnotationAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__NameAssignment_1"


    // $ANTLR start "rule__Annotation__ValueAssignment_2_1"
    // InternalMORA.g:4296:1: rule__Annotation__ValueAssignment_2_1 : ( RULE_STRING ) ;
    public final void rule__Annotation__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4300:1: ( ( RULE_STRING ) )
            // InternalMORA.g:4301:2: ( RULE_STRING )
            {
            // InternalMORA.g:4301:2: ( RULE_STRING )
            // InternalMORA.g:4302:3: RULE_STRING
            {
             before(grammarAccess.getAnnotationAccess().getValueSTRINGTerminalRuleCall_2_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getAnnotationAccess().getValueSTRINGTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Annotation__ValueAssignment_2_1"


    // $ANTLR start "rule__StructDecl__DocAssignment_0"
    // InternalMORA.g:4311:1: rule__StructDecl__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__StructDecl__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4315:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4316:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4316:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4317:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getStructDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getStructDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__DocAssignment_0"


    // $ANTLR start "rule__StructDecl__AnnoAssignment_1"
    // InternalMORA.g:4326:1: rule__StructDecl__AnnoAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__StructDecl__AnnoAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4330:1: ( ( ruleAnnotation ) )
            // InternalMORA.g:4331:2: ( ruleAnnotation )
            {
            // InternalMORA.g:4331:2: ( ruleAnnotation )
            // InternalMORA.g:4332:3: ruleAnnotation
            {
             before(grammarAccess.getStructDeclAccess().getAnnoAnnotationParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAnnotation();

            state._fsp--;

             after(grammarAccess.getStructDeclAccess().getAnnoAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__AnnoAssignment_1"


    // $ANTLR start "rule__StructDecl__NameAssignment_3"
    // InternalMORA.g:4341:1: rule__StructDecl__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__StructDecl__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4345:1: ( ( RULE_ID ) )
            // InternalMORA.g:4346:2: ( RULE_ID )
            {
            // InternalMORA.g:4346:2: ( RULE_ID )
            // InternalMORA.g:4347:3: RULE_ID
            {
             before(grammarAccess.getStructDeclAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getStructDeclAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__NameAssignment_3"


    // $ANTLR start "rule__StructDecl__MemberAssignment_5_0"
    // InternalMORA.g:4356:1: rule__StructDecl__MemberAssignment_5_0 : ( ruleMember ) ;
    public final void rule__StructDecl__MemberAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4360:1: ( ( ruleMember ) )
            // InternalMORA.g:4361:2: ( ruleMember )
            {
            // InternalMORA.g:4361:2: ( ruleMember )
            // InternalMORA.g:4362:3: ruleMember
            {
             before(grammarAccess.getStructDeclAccess().getMemberMemberParserRuleCall_5_0_0()); 
            pushFollow(FOLLOW_2);
            ruleMember();

            state._fsp--;

             after(grammarAccess.getStructDeclAccess().getMemberMemberParserRuleCall_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StructDecl__MemberAssignment_5_0"


    // $ANTLR start "rule__Member__DocAssignment_0"
    // InternalMORA.g:4371:1: rule__Member__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__Member__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4375:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4376:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4376:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4377:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getMemberAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getMemberAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__DocAssignment_0"


    // $ANTLR start "rule__Member__AnnoAssignment_1"
    // InternalMORA.g:4386:1: rule__Member__AnnoAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Member__AnnoAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4390:1: ( ( ruleAnnotation ) )
            // InternalMORA.g:4391:2: ( ruleAnnotation )
            {
            // InternalMORA.g:4391:2: ( ruleAnnotation )
            // InternalMORA.g:4392:3: ruleAnnotation
            {
             before(grammarAccess.getMemberAccess().getAnnoAnnotationParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAnnotation();

            state._fsp--;

             after(grammarAccess.getMemberAccess().getAnnoAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__AnnoAssignment_1"


    // $ANTLR start "rule__Member__ComplexTypeAssignment_2_0"
    // InternalMORA.g:4401:1: rule__Member__ComplexTypeAssignment_2_0 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Member__ComplexTypeAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4405:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4406:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4406:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4407:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getMemberAccess().getComplexTypeTypeDeclCrossReference_2_0_0()); 
            // InternalMORA.g:4408:3: ( ruleQualifiedName )
            // InternalMORA.g:4409:4: ruleQualifiedName
            {
             before(grammarAccess.getMemberAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_2_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getMemberAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_2_0_0_1()); 

            }

             after(grammarAccess.getMemberAccess().getComplexTypeTypeDeclCrossReference_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__ComplexTypeAssignment_2_0"


    // $ANTLR start "rule__Member__PrimTypeAssignment_2_1"
    // InternalMORA.g:4420:1: rule__Member__PrimTypeAssignment_2_1 : ( rulePrimTypeLiteral ) ;
    public final void rule__Member__PrimTypeAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4424:1: ( ( rulePrimTypeLiteral ) )
            // InternalMORA.g:4425:2: ( rulePrimTypeLiteral )
            {
            // InternalMORA.g:4425:2: ( rulePrimTypeLiteral )
            // InternalMORA.g:4426:3: rulePrimTypeLiteral
            {
             before(grammarAccess.getMemberAccess().getPrimTypePrimTypeLiteralEnumRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            rulePrimTypeLiteral();

            state._fsp--;

             after(grammarAccess.getMemberAccess().getPrimTypePrimTypeLiteralEnumRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__PrimTypeAssignment_2_1"


    // $ANTLR start "rule__Member__NameAssignment_3"
    // InternalMORA.g:4435:1: rule__Member__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Member__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4439:1: ( ( RULE_ID ) )
            // InternalMORA.g:4440:2: ( RULE_ID )
            {
            // InternalMORA.g:4440:2: ( RULE_ID )
            // InternalMORA.g:4441:3: RULE_ID
            {
             before(grammarAccess.getMemberAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMemberAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Member__NameAssignment_3"


    // $ANTLR start "rule__EnumDecl__DocAssignment_0"
    // InternalMORA.g:4450:1: rule__EnumDecl__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__EnumDecl__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4454:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4455:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4455:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4456:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getEnumDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getEnumDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__DocAssignment_0"


    // $ANTLR start "rule__EnumDecl__NameAssignment_2"
    // InternalMORA.g:4465:1: rule__EnumDecl__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__EnumDecl__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4469:1: ( ( RULE_ID ) )
            // InternalMORA.g:4470:2: ( RULE_ID )
            {
            // InternalMORA.g:4470:2: ( RULE_ID )
            // InternalMORA.g:4471:3: RULE_ID
            {
             before(grammarAccess.getEnumDeclAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getEnumDeclAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__NameAssignment_2"


    // $ANTLR start "rule__EnumDecl__LiteralsAssignment_4_0"
    // InternalMORA.g:4480:1: rule__EnumDecl__LiteralsAssignment_4_0 : ( ruleLiteral ) ;
    public final void rule__EnumDecl__LiteralsAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4484:1: ( ( ruleLiteral ) )
            // InternalMORA.g:4485:2: ( ruleLiteral )
            {
            // InternalMORA.g:4485:2: ( ruleLiteral )
            // InternalMORA.g:4486:3: ruleLiteral
            {
             before(grammarAccess.getEnumDeclAccess().getLiteralsLiteralParserRuleCall_4_0_0()); 
            pushFollow(FOLLOW_2);
            ruleLiteral();

            state._fsp--;

             after(grammarAccess.getEnumDeclAccess().getLiteralsLiteralParserRuleCall_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EnumDecl__LiteralsAssignment_4_0"


    // $ANTLR start "rule__Literal__DocAssignment_0"
    // InternalMORA.g:4495:1: rule__Literal__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__Literal__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4499:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4500:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4500:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4501:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getLiteralAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getLiteralAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__DocAssignment_0"


    // $ANTLR start "rule__Literal__NameAssignment_1"
    // InternalMORA.g:4510:1: rule__Literal__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Literal__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4514:1: ( ( RULE_ID ) )
            // InternalMORA.g:4515:2: ( RULE_ID )
            {
            // InternalMORA.g:4515:2: ( RULE_ID )
            // InternalMORA.g:4516:3: RULE_ID
            {
             before(grammarAccess.getLiteralAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLiteralAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__NameAssignment_1"


    // $ANTLR start "rule__Literal__ValueAssignment_2_1"
    // InternalMORA.g:4525:1: rule__Literal__ValueAssignment_2_1 : ( RULE_INT ) ;
    public final void rule__Literal__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4529:1: ( ( RULE_INT ) )
            // InternalMORA.g:4530:2: ( RULE_INT )
            {
            // InternalMORA.g:4530:2: ( RULE_INT )
            // InternalMORA.g:4531:3: RULE_INT
            {
             before(grammarAccess.getLiteralAccess().getValueINTTerminalRuleCall_2_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getLiteralAccess().getValueINTTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__ValueAssignment_2_1"


    // $ANTLR start "rule__ListTypeDecl__DocAssignment_0"
    // InternalMORA.g:4540:1: rule__ListTypeDecl__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__ListTypeDecl__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4544:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4545:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4545:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4546:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getListTypeDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getListTypeDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__DocAssignment_0"


    // $ANTLR start "rule__ListTypeDecl__ValueTypeAssignment_3_0"
    // InternalMORA.g:4555:1: rule__ListTypeDecl__ValueTypeAssignment_3_0 : ( ( ruleQualifiedName ) ) ;
    public final void rule__ListTypeDecl__ValueTypeAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4559:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4560:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4560:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4561:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getListTypeDeclAccess().getValueTypeSingleTypeDeclCrossReference_3_0_0()); 
            // InternalMORA.g:4562:3: ( ruleQualifiedName )
            // InternalMORA.g:4563:4: ruleQualifiedName
            {
             before(grammarAccess.getListTypeDeclAccess().getValueTypeSingleTypeDeclQualifiedNameParserRuleCall_3_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getListTypeDeclAccess().getValueTypeSingleTypeDeclQualifiedNameParserRuleCall_3_0_0_1()); 

            }

             after(grammarAccess.getListTypeDeclAccess().getValueTypeSingleTypeDeclCrossReference_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__ValueTypeAssignment_3_0"


    // $ANTLR start "rule__ListTypeDecl__PrimTypeAssignment_3_1"
    // InternalMORA.g:4574:1: rule__ListTypeDecl__PrimTypeAssignment_3_1 : ( rulePrimTypeLiteral ) ;
    public final void rule__ListTypeDecl__PrimTypeAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4578:1: ( ( rulePrimTypeLiteral ) )
            // InternalMORA.g:4579:2: ( rulePrimTypeLiteral )
            {
            // InternalMORA.g:4579:2: ( rulePrimTypeLiteral )
            // InternalMORA.g:4580:3: rulePrimTypeLiteral
            {
             before(grammarAccess.getListTypeDeclAccess().getPrimTypePrimTypeLiteralEnumRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            rulePrimTypeLiteral();

            state._fsp--;

             after(grammarAccess.getListTypeDeclAccess().getPrimTypePrimTypeLiteralEnumRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__PrimTypeAssignment_3_1"


    // $ANTLR start "rule__ListTypeDecl__NameAssignment_5"
    // InternalMORA.g:4589:1: rule__ListTypeDecl__NameAssignment_5 : ( RULE_ID ) ;
    public final void rule__ListTypeDecl__NameAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4593:1: ( ( RULE_ID ) )
            // InternalMORA.g:4594:2: ( RULE_ID )
            {
            // InternalMORA.g:4594:2: ( RULE_ID )
            // InternalMORA.g:4595:3: RULE_ID
            {
             before(grammarAccess.getListTypeDeclAccess().getNameIDTerminalRuleCall_5_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getListTypeDeclAccess().getNameIDTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ListTypeDecl__NameAssignment_5"


    // $ANTLR start "rule__Interface__DocAssignment_0"
    // InternalMORA.g:4604:1: rule__Interface__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__Interface__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4608:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4609:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4609:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4610:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getInterfaceAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__DocAssignment_0"


    // $ANTLR start "rule__Interface__AnnoAssignment_1"
    // InternalMORA.g:4619:1: rule__Interface__AnnoAssignment_1 : ( ruleAnnotation ) ;
    public final void rule__Interface__AnnoAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4623:1: ( ( ruleAnnotation ) )
            // InternalMORA.g:4624:2: ( ruleAnnotation )
            {
            // InternalMORA.g:4624:2: ( ruleAnnotation )
            // InternalMORA.g:4625:3: ruleAnnotation
            {
             before(grammarAccess.getInterfaceAccess().getAnnoAnnotationParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAnnotation();

            state._fsp--;

             after(grammarAccess.getInterfaceAccess().getAnnoAnnotationParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__AnnoAssignment_1"


    // $ANTLR start "rule__Interface__NameAssignment_3"
    // InternalMORA.g:4634:1: rule__Interface__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__Interface__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4638:1: ( ( RULE_ID ) )
            // InternalMORA.g:4639:2: ( RULE_ID )
            {
            // InternalMORA.g:4639:2: ( RULE_ID )
            // InternalMORA.g:4640:3: RULE_ID
            {
             before(grammarAccess.getInterfaceAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getInterfaceAccess().getNameIDTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__NameAssignment_3"


    // $ANTLR start "rule__Interface__ParentsAssignment_4_1"
    // InternalMORA.g:4649:1: rule__Interface__ParentsAssignment_4_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Interface__ParentsAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4653:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4654:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4654:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4655:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_1_0()); 
            // InternalMORA.g:4656:3: ( ruleQualifiedName )
            // InternalMORA.g:4657:4: ruleQualifiedName
            {
             before(grammarAccess.getInterfaceAccess().getParentsInterfaceQualifiedNameParserRuleCall_4_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getInterfaceAccess().getParentsInterfaceQualifiedNameParserRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__ParentsAssignment_4_1"


    // $ANTLR start "rule__Interface__ParentsAssignment_4_2_1"
    // InternalMORA.g:4668:1: rule__Interface__ParentsAssignment_4_2_1 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Interface__ParentsAssignment_4_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4672:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4673:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4673:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4674:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_2_1_0()); 
            // InternalMORA.g:4675:3: ( ruleQualifiedName )
            // InternalMORA.g:4676:4: ruleQualifiedName
            {
             before(grammarAccess.getInterfaceAccess().getParentsInterfaceQualifiedNameParserRuleCall_4_2_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getInterfaceAccess().getParentsInterfaceQualifiedNameParserRuleCall_4_2_1_0_1()); 

            }

             after(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__ParentsAssignment_4_2_1"


    // $ANTLR start "rule__Interface__MethodsAssignment_6_0"
    // InternalMORA.g:4687:1: rule__Interface__MethodsAssignment_6_0 : ( ruleMethod ) ;
    public final void rule__Interface__MethodsAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4691:1: ( ( ruleMethod ) )
            // InternalMORA.g:4692:2: ( ruleMethod )
            {
            // InternalMORA.g:4692:2: ( ruleMethod )
            // InternalMORA.g:4693:3: ruleMethod
            {
             before(grammarAccess.getInterfaceAccess().getMethodsMethodParserRuleCall_6_0_0()); 
            pushFollow(FOLLOW_2);
            ruleMethod();

            state._fsp--;

             after(grammarAccess.getInterfaceAccess().getMethodsMethodParserRuleCall_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Interface__MethodsAssignment_6_0"


    // $ANTLR start "rule__Method__DocAssignment_0"
    // InternalMORA.g:4702:1: rule__Method__DocAssignment_0 : ( RULE_ML_COMMENT ) ;
    public final void rule__Method__DocAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4706:1: ( ( RULE_ML_COMMENT ) )
            // InternalMORA.g:4707:2: ( RULE_ML_COMMENT )
            {
            // InternalMORA.g:4707:2: ( RULE_ML_COMMENT )
            // InternalMORA.g:4708:3: RULE_ML_COMMENT
            {
             before(grammarAccess.getMethodAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getDocML_COMMENTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__DocAssignment_0"


    // $ANTLR start "rule__Method__ReturnProxyTypeAssignment_1_0_0"
    // InternalMORA.g:4717:1: rule__Method__ReturnProxyTypeAssignment_1_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__Method__ReturnProxyTypeAssignment_1_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4721:1: ( ( ( RULE_ID ) ) )
            // InternalMORA.g:4722:2: ( ( RULE_ID ) )
            {
            // InternalMORA.g:4722:2: ( ( RULE_ID ) )
            // InternalMORA.g:4723:3: ( RULE_ID )
            {
             before(grammarAccess.getMethodAccess().getReturnProxyTypeInterfaceCrossReference_1_0_0_0()); 
            // InternalMORA.g:4724:3: ( RULE_ID )
            // InternalMORA.g:4725:4: RULE_ID
            {
             before(grammarAccess.getMethodAccess().getReturnProxyTypeInterfaceIDTerminalRuleCall_1_0_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getReturnProxyTypeInterfaceIDTerminalRuleCall_1_0_0_0_1()); 

            }

             after(grammarAccess.getMethodAccess().getReturnProxyTypeInterfaceCrossReference_1_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ReturnProxyTypeAssignment_1_0_0"


    // $ANTLR start "rule__Method__ComplexTypeAssignment_1_1_0"
    // InternalMORA.g:4736:1: rule__Method__ComplexTypeAssignment_1_1_0 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Method__ComplexTypeAssignment_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4740:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4741:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4741:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4742:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getMethodAccess().getComplexTypeTypeDeclCrossReference_1_1_0_0()); 
            // InternalMORA.g:4743:3: ( ruleQualifiedName )
            // InternalMORA.g:4744:4: ruleQualifiedName
            {
             before(grammarAccess.getMethodAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_1_1_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_1_1_0_0_1()); 

            }

             after(grammarAccess.getMethodAccess().getComplexTypeTypeDeclCrossReference_1_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ComplexTypeAssignment_1_1_0"


    // $ANTLR start "rule__Method__PrimTypeAssignment_1_1_1"
    // InternalMORA.g:4755:1: rule__Method__PrimTypeAssignment_1_1_1 : ( rulePrimTypeLiteral ) ;
    public final void rule__Method__PrimTypeAssignment_1_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4759:1: ( ( rulePrimTypeLiteral ) )
            // InternalMORA.g:4760:2: ( rulePrimTypeLiteral )
            {
            // InternalMORA.g:4760:2: ( rulePrimTypeLiteral )
            // InternalMORA.g:4761:3: rulePrimTypeLiteral
            {
             before(grammarAccess.getMethodAccess().getPrimTypePrimTypeLiteralEnumRuleCall_1_1_1_0()); 
            pushFollow(FOLLOW_2);
            rulePrimTypeLiteral();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getPrimTypePrimTypeLiteralEnumRuleCall_1_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__PrimTypeAssignment_1_1_1"


    // $ANTLR start "rule__Method__NameAssignment_2"
    // InternalMORA.g:4770:1: rule__Method__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Method__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4774:1: ( ( RULE_ID ) )
            // InternalMORA.g:4775:2: ( RULE_ID )
            {
            // InternalMORA.g:4775:2: ( RULE_ID )
            // InternalMORA.g:4776:3: RULE_ID
            {
             before(grammarAccess.getMethodAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMethodAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__NameAssignment_2"


    // $ANTLR start "rule__Method__ParametersAssignment_4_0"
    // InternalMORA.g:4785:1: rule__Method__ParametersAssignment_4_0 : ( ruleParameter ) ;
    public final void rule__Method__ParametersAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4789:1: ( ( ruleParameter ) )
            // InternalMORA.g:4790:2: ( ruleParameter )
            {
            // InternalMORA.g:4790:2: ( ruleParameter )
            // InternalMORA.g:4791:3: ruleParameter
            {
             before(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_0_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ParametersAssignment_4_0"


    // $ANTLR start "rule__Method__ParametersAssignment_4_1_1"
    // InternalMORA.g:4800:1: rule__Method__ParametersAssignment_4_1_1 : ( ruleParameter ) ;
    public final void rule__Method__ParametersAssignment_4_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4804:1: ( ( ruleParameter ) )
            // InternalMORA.g:4805:2: ( ruleParameter )
            {
            // InternalMORA.g:4805:2: ( ruleParameter )
            // InternalMORA.g:4806:3: ruleParameter
            {
             before(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleParameter();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ParametersAssignment_4_1_1"


    // $ANTLR start "rule__Method__ExceptionsAssignment_6_1"
    // InternalMORA.g:4815:1: rule__Method__ExceptionsAssignment_6_1 : ( ruleException ) ;
    public final void rule__Method__ExceptionsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4819:1: ( ( ruleException ) )
            // InternalMORA.g:4820:2: ( ruleException )
            {
            // InternalMORA.g:4820:2: ( ruleException )
            // InternalMORA.g:4821:3: ruleException
            {
             before(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_1_0()); 
            pushFollow(FOLLOW_2);
            ruleException();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ExceptionsAssignment_6_1"


    // $ANTLR start "rule__Method__ExceptionsAssignment_6_2_1"
    // InternalMORA.g:4830:1: rule__Method__ExceptionsAssignment_6_2_1 : ( ruleException ) ;
    public final void rule__Method__ExceptionsAssignment_6_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4834:1: ( ( ruleException ) )
            // InternalMORA.g:4835:2: ( ruleException )
            {
            // InternalMORA.g:4835:2: ( ruleException )
            // InternalMORA.g:4836:3: ruleException
            {
             before(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleException();

            state._fsp--;

             after(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Method__ExceptionsAssignment_6_2_1"


    // $ANTLR start "rule__Exception__NameAssignment_1"
    // InternalMORA.g:4845:1: rule__Exception__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Exception__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4849:1: ( ( RULE_ID ) )
            // InternalMORA.g:4850:2: ( RULE_ID )
            {
            // InternalMORA.g:4850:2: ( RULE_ID )
            // InternalMORA.g:4851:3: RULE_ID
            {
             before(grammarAccess.getExceptionAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getExceptionAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__NameAssignment_1"


    // $ANTLR start "rule__Exception__MemberAssignment_3_0"
    // InternalMORA.g:4860:1: rule__Exception__MemberAssignment_3_0 : ( ruleMember ) ;
    public final void rule__Exception__MemberAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4864:1: ( ( ruleMember ) )
            // InternalMORA.g:4865:2: ( ruleMember )
            {
            // InternalMORA.g:4865:2: ( ruleMember )
            // InternalMORA.g:4866:3: ruleMember
            {
             before(grammarAccess.getExceptionAccess().getMemberMemberParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleMember();

            state._fsp--;

             after(grammarAccess.getExceptionAccess().getMemberMemberParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Exception__MemberAssignment_3_0"


    // $ANTLR start "rule__Parameter__ComplexTypeAssignment_0_0"
    // InternalMORA.g:4875:1: rule__Parameter__ComplexTypeAssignment_0_0 : ( ( ruleQualifiedName ) ) ;
    public final void rule__Parameter__ComplexTypeAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4879:1: ( ( ( ruleQualifiedName ) ) )
            // InternalMORA.g:4880:2: ( ( ruleQualifiedName ) )
            {
            // InternalMORA.g:4880:2: ( ( ruleQualifiedName ) )
            // InternalMORA.g:4881:3: ( ruleQualifiedName )
            {
             before(grammarAccess.getParameterAccess().getComplexTypeTypeDeclCrossReference_0_0_0()); 
            // InternalMORA.g:4882:3: ( ruleQualifiedName )
            // InternalMORA.g:4883:4: ruleQualifiedName
            {
             before(grammarAccess.getParameterAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_0_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedName();

            state._fsp--;

             after(grammarAccess.getParameterAccess().getComplexTypeTypeDeclQualifiedNameParserRuleCall_0_0_0_1()); 

            }

             after(grammarAccess.getParameterAccess().getComplexTypeTypeDeclCrossReference_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__ComplexTypeAssignment_0_0"


    // $ANTLR start "rule__Parameter__PrimTypeAssignment_0_1"
    // InternalMORA.g:4894:1: rule__Parameter__PrimTypeAssignment_0_1 : ( rulePrimTypeLiteral ) ;
    public final void rule__Parameter__PrimTypeAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4898:1: ( ( rulePrimTypeLiteral ) )
            // InternalMORA.g:4899:2: ( rulePrimTypeLiteral )
            {
            // InternalMORA.g:4899:2: ( rulePrimTypeLiteral )
            // InternalMORA.g:4900:3: rulePrimTypeLiteral
            {
             before(grammarAccess.getParameterAccess().getPrimTypePrimTypeLiteralEnumRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            rulePrimTypeLiteral();

            state._fsp--;

             after(grammarAccess.getParameterAccess().getPrimTypePrimTypeLiteralEnumRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__PrimTypeAssignment_0_1"


    // $ANTLR start "rule__Parameter__ProxyTypeAssignment_0_2_0"
    // InternalMORA.g:4909:1: rule__Parameter__ProxyTypeAssignment_0_2_0 : ( ( RULE_ID ) ) ;
    public final void rule__Parameter__ProxyTypeAssignment_0_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4913:1: ( ( ( RULE_ID ) ) )
            // InternalMORA.g:4914:2: ( ( RULE_ID ) )
            {
            // InternalMORA.g:4914:2: ( ( RULE_ID ) )
            // InternalMORA.g:4915:3: ( RULE_ID )
            {
             before(grammarAccess.getParameterAccess().getProxyTypeInterfaceCrossReference_0_2_0_0()); 
            // InternalMORA.g:4916:3: ( RULE_ID )
            // InternalMORA.g:4917:4: RULE_ID
            {
             before(grammarAccess.getParameterAccess().getProxyTypeInterfaceIDTerminalRuleCall_0_2_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getProxyTypeInterfaceIDTerminalRuleCall_0_2_0_0_1()); 

            }

             after(grammarAccess.getParameterAccess().getProxyTypeInterfaceCrossReference_0_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__ProxyTypeAssignment_0_2_0"


    // $ANTLR start "rule__Parameter__NameAssignment_1"
    // InternalMORA.g:4928:1: rule__Parameter__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Parameter__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMORA.g:4932:1: ( ( RULE_ID ) )
            // InternalMORA.g:4933:2: ( RULE_ID )
            {
            // InternalMORA.g:4933:2: ( RULE_ID )
            // InternalMORA.g:4934:3: RULE_ID
            {
             before(grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Parameter__NameAssignment_1"

    // Delegated rules


    protected DFA1 dfa1 = new DFA1(this);
    static final String dfa_1s = "\10\uffff";
    static final String dfa_2s = "\1\6\1\42\1\4\2\uffff\1\36\1\5\1\42";
    static final String dfa_3s = "\2\50\1\4\2\uffff\1\50\1\5\1\50";
    static final String dfa_4s = "\3\uffff\1\1\1\2\3\uffff";
    static final String dfa_5s = "\10\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\6\uffff\11\4\14\uffff\1\2\3\4\2\uffff\1\3",
            "\1\2\3\4\2\uffff\1\3",
            "\1\5",
            "",
            "",
            "\1\6\3\uffff\1\2\1\4\4\uffff\1\3",
            "\1\7",
            "\1\2\1\4\4\uffff\1\3"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "568:1: rule__Model__Alternatives_5_0 : ( ( ( rule__Model__InterfacesAssignment_5_0_0 ) ) | ( ( rule__Model__TypesAssignment_5_0_1 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000008400000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000013C013FE040L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000013C003FE042L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000290000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000290000002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000C00000040L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000004013FE050L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x00000004003FE052L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000004003FE050L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000001C003FE040L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000052L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000013C003FE040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000003FE010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000010400000040L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000020000800000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00000000013FE050L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00000000003FE052L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00000000003FE050L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00001000003FE010L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000001800L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000001802L});

}