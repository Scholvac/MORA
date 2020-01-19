package de.sos.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.sos.services.MORAGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMORAParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_INT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'package'", "'{'", "';'", "'}'", "'import'", "'options'", "'java'", "'base-package'", "'='", "'csharp'", "'base-namespace'", "'cpp'", "'@'", "'struct'", "'enum'", "'List'", "'<'", "'>'", "'interface'", "'extends'", "','", "'*'", "'('", "')'", "'throws'", "'exception'", "'.'", "'::'", "'bool'", "'byte'", "'short'", "'int'", "'long'", "'float'", "'double'", "'string'", "'void'"
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

        public InternalMORAParser(TokenStream input, MORAGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected MORAGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalMORA.g:65:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalMORA.g:65:46: (iv_ruleModel= ruleModel EOF )
            // InternalMORA.g:66:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMORA.g:72:1: ruleModel returns [EObject current=null] : ( ( (lv_includes_0_0= ruleInclude ) )* ( (lv_options_1_0= ruleOptions ) )? otherlv_2= 'package' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )* otherlv_8= '}' ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_includes_0_0 = null;

        EObject lv_options_1_0 = null;

        EObject lv_interfaces_5_0 = null;

        EObject lv_types_6_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:78:2: ( ( ( (lv_includes_0_0= ruleInclude ) )* ( (lv_options_1_0= ruleOptions ) )? otherlv_2= 'package' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )* otherlv_8= '}' ) )
            // InternalMORA.g:79:2: ( ( (lv_includes_0_0= ruleInclude ) )* ( (lv_options_1_0= ruleOptions ) )? otherlv_2= 'package' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )* otherlv_8= '}' )
            {
            // InternalMORA.g:79:2: ( ( (lv_includes_0_0= ruleInclude ) )* ( (lv_options_1_0= ruleOptions ) )? otherlv_2= 'package' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )* otherlv_8= '}' )
            // InternalMORA.g:80:3: ( (lv_includes_0_0= ruleInclude ) )* ( (lv_options_1_0= ruleOptions ) )? otherlv_2= 'package' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )* otherlv_8= '}'
            {
            // InternalMORA.g:80:3: ( (lv_includes_0_0= ruleInclude ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMORA.g:81:4: (lv_includes_0_0= ruleInclude )
            	    {
            	    // InternalMORA.g:81:4: (lv_includes_0_0= ruleInclude )
            	    // InternalMORA.g:82:5: lv_includes_0_0= ruleInclude
            	    {

            	    					newCompositeNode(grammarAccess.getModelAccess().getIncludesIncludeParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_includes_0_0=ruleInclude();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"includes",
            	    						lv_includes_0_0,
            	    						"de.sos.MORA.Include");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalMORA.g:99:3: ( (lv_options_1_0= ruleOptions ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==16) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalMORA.g:100:4: (lv_options_1_0= ruleOptions )
                    {
                    // InternalMORA.g:100:4: (lv_options_1_0= ruleOptions )
                    // InternalMORA.g:101:5: lv_options_1_0= ruleOptions
                    {

                    					newCompositeNode(grammarAccess.getModelAccess().getOptionsOptionsParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_4);
                    lv_options_1_0=ruleOptions();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModelRule());
                    					}
                    					set(
                    						current,
                    						"options",
                    						lv_options_1_0,
                    						"de.sos.MORA.Options");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,11,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getModelAccess().getPackageKeyword_2());
            		
            // InternalMORA.g:122:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalMORA.g:123:4: (lv_name_3_0= RULE_ID )
            {
            // InternalMORA.g:123:4: (lv_name_3_0= RULE_ID )
            // InternalMORA.g:124:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_3_0, grammarAccess.getModelAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getModelRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_3_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_7); 

            			newLeafNode(otherlv_4, grammarAccess.getModelAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMORA.g:144:3: ( ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )? )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ML_COMMENT||(LA5_0>=23 && LA5_0<=26)||LA5_0==29||(LA5_0>=39 && LA5_0<=47)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalMORA.g:145:4: ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) ) (otherlv_7= ';' )?
            	    {
            	    // InternalMORA.g:145:4: ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) )
            	    int alt3=2;
            	    alt3 = dfa3.predict(input);
            	    switch (alt3) {
            	        case 1 :
            	            // InternalMORA.g:146:5: ( (lv_interfaces_5_0= ruleInterface ) )
            	            {
            	            // InternalMORA.g:146:5: ( (lv_interfaces_5_0= ruleInterface ) )
            	            // InternalMORA.g:147:6: (lv_interfaces_5_0= ruleInterface )
            	            {
            	            // InternalMORA.g:147:6: (lv_interfaces_5_0= ruleInterface )
            	            // InternalMORA.g:148:7: lv_interfaces_5_0= ruleInterface
            	            {

            	            							newCompositeNode(grammarAccess.getModelAccess().getInterfacesInterfaceParserRuleCall_5_0_0_0());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_interfaces_5_0=ruleInterface();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getModelRule());
            	            							}
            	            							add(
            	            								current,
            	            								"interfaces",
            	            								lv_interfaces_5_0,
            	            								"de.sos.MORA.Interface");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }


            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalMORA.g:166:5: ( (lv_types_6_0= ruleTypeDecl ) )
            	            {
            	            // InternalMORA.g:166:5: ( (lv_types_6_0= ruleTypeDecl ) )
            	            // InternalMORA.g:167:6: (lv_types_6_0= ruleTypeDecl )
            	            {
            	            // InternalMORA.g:167:6: (lv_types_6_0= ruleTypeDecl )
            	            // InternalMORA.g:168:7: lv_types_6_0= ruleTypeDecl
            	            {

            	            							newCompositeNode(grammarAccess.getModelAccess().getTypesTypeDeclParserRuleCall_5_0_1_0());
            	            						
            	            pushFollow(FOLLOW_8);
            	            lv_types_6_0=ruleTypeDecl();

            	            state._fsp--;


            	            							if (current==null) {
            	            								current = createModelElementForParent(grammarAccess.getModelRule());
            	            							}
            	            							add(
            	            								current,
            	            								"types",
            	            								lv_types_6_0,
            	            								"de.sos.MORA.TypeDecl");
            	            							afterParserOrEnumRuleCall();
            	            						

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    // InternalMORA.g:186:4: (otherlv_7= ';' )?
            	    int alt4=2;
            	    int LA4_0 = input.LA(1);

            	    if ( (LA4_0==13) ) {
            	        alt4=1;
            	    }
            	    switch (alt4) {
            	        case 1 :
            	            // InternalMORA.g:187:5: otherlv_7= ';'
            	            {
            	            otherlv_7=(Token)match(input,13,FOLLOW_7); 

            	            					newLeafNode(otherlv_7, grammarAccess.getModelAccess().getSemicolonKeyword_5_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_8=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getModelAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleInclude"
    // InternalMORA.g:201:1: entryRuleInclude returns [EObject current=null] : iv_ruleInclude= ruleInclude EOF ;
    public final EObject entryRuleInclude() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInclude = null;


        try {
            // InternalMORA.g:201:48: (iv_ruleInclude= ruleInclude EOF )
            // InternalMORA.g:202:2: iv_ruleInclude= ruleInclude EOF
            {
             newCompositeNode(grammarAccess.getIncludeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInclude=ruleInclude();

            state._fsp--;

             current =iv_ruleInclude; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInclude"


    // $ANTLR start "ruleInclude"
    // InternalMORA.g:208:1: ruleInclude returns [EObject current=null] : (otherlv_0= 'import' ( (lv_importUri_1_0= RULE_STRING ) ) (otherlv_2= ';' )? ) ;
    public final EObject ruleInclude() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_importUri_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalMORA.g:214:2: ( (otherlv_0= 'import' ( (lv_importUri_1_0= RULE_STRING ) ) (otherlv_2= ';' )? ) )
            // InternalMORA.g:215:2: (otherlv_0= 'import' ( (lv_importUri_1_0= RULE_STRING ) ) (otherlv_2= ';' )? )
            {
            // InternalMORA.g:215:2: (otherlv_0= 'import' ( (lv_importUri_1_0= RULE_STRING ) ) (otherlv_2= ';' )? )
            // InternalMORA.g:216:3: otherlv_0= 'import' ( (lv_importUri_1_0= RULE_STRING ) ) (otherlv_2= ';' )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getIncludeAccess().getImportKeyword_0());
            		
            // InternalMORA.g:220:3: ( (lv_importUri_1_0= RULE_STRING ) )
            // InternalMORA.g:221:4: (lv_importUri_1_0= RULE_STRING )
            {
            // InternalMORA.g:221:4: (lv_importUri_1_0= RULE_STRING )
            // InternalMORA.g:222:5: lv_importUri_1_0= RULE_STRING
            {
            lv_importUri_1_0=(Token)match(input,RULE_STRING,FOLLOW_10); 

            					newLeafNode(lv_importUri_1_0, grammarAccess.getIncludeAccess().getImportUriSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIncludeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"importUri",
            						lv_importUri_1_0,
            						"de.sos.MORA.STRING");
            				

            }


            }

            // InternalMORA.g:238:3: (otherlv_2= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==13) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalMORA.g:239:4: otherlv_2= ';'
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getIncludeAccess().getSemicolonKeyword_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInclude"


    // $ANTLR start "entryRuleOptions"
    // InternalMORA.g:248:1: entryRuleOptions returns [EObject current=null] : iv_ruleOptions= ruleOptions EOF ;
    public final EObject entryRuleOptions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptions = null;


        try {
            // InternalMORA.g:248:48: (iv_ruleOptions= ruleOptions EOF )
            // InternalMORA.g:249:2: iv_ruleOptions= ruleOptions EOF
            {
             newCompositeNode(grammarAccess.getOptionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptions=ruleOptions();

            state._fsp--;

             current =iv_ruleOptions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptions"


    // $ANTLR start "ruleOptions"
    // InternalMORA.g:255:1: ruleOptions returns [EObject current=null] : (otherlv_0= 'options' otherlv_1= '{' ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+ otherlv_5= '}' ) ;
    public final EObject ruleOptions() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_5=null;
        EObject lv_javaOptions_2_0 = null;

        EObject lv_csOptions_3_0 = null;

        EObject lv_cppOptions_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:261:2: ( (otherlv_0= 'options' otherlv_1= '{' ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+ otherlv_5= '}' ) )
            // InternalMORA.g:262:2: (otherlv_0= 'options' otherlv_1= '{' ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+ otherlv_5= '}' )
            {
            // InternalMORA.g:262:2: (otherlv_0= 'options' otherlv_1= '{' ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+ otherlv_5= '}' )
            // InternalMORA.g:263:3: otherlv_0= 'options' otherlv_1= '{' ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+ otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getOptionsAccess().getOptionsKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getOptionsAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalMORA.g:271:3: ( ( (lv_javaOptions_2_0= ruleJavaOptions ) ) | ( (lv_csOptions_3_0= ruleCSharpOptions ) ) | ( (lv_cppOptions_4_0= ruleCppOptions ) ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=4;
                switch ( input.LA(1) ) {
                case 17:
                    {
                    alt7=1;
                    }
                    break;
                case 20:
                    {
                    alt7=2;
                    }
                    break;
                case 22:
                    {
                    alt7=3;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // InternalMORA.g:272:4: ( (lv_javaOptions_2_0= ruleJavaOptions ) )
            	    {
            	    // InternalMORA.g:272:4: ( (lv_javaOptions_2_0= ruleJavaOptions ) )
            	    // InternalMORA.g:273:5: (lv_javaOptions_2_0= ruleJavaOptions )
            	    {
            	    // InternalMORA.g:273:5: (lv_javaOptions_2_0= ruleJavaOptions )
            	    // InternalMORA.g:274:6: lv_javaOptions_2_0= ruleJavaOptions
            	    {

            	    						newCompositeNode(grammarAccess.getOptionsAccess().getJavaOptionsJavaOptionsParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_javaOptions_2_0=ruleJavaOptions();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOptionsRule());
            	    						}
            	    						set(
            	    							current,
            	    							"javaOptions",
            	    							lv_javaOptions_2_0,
            	    							"de.sos.MORA.JavaOptions");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalMORA.g:292:4: ( (lv_csOptions_3_0= ruleCSharpOptions ) )
            	    {
            	    // InternalMORA.g:292:4: ( (lv_csOptions_3_0= ruleCSharpOptions ) )
            	    // InternalMORA.g:293:5: (lv_csOptions_3_0= ruleCSharpOptions )
            	    {
            	    // InternalMORA.g:293:5: (lv_csOptions_3_0= ruleCSharpOptions )
            	    // InternalMORA.g:294:6: lv_csOptions_3_0= ruleCSharpOptions
            	    {

            	    						newCompositeNode(grammarAccess.getOptionsAccess().getCsOptionsCSharpOptionsParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_csOptions_3_0=ruleCSharpOptions();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOptionsRule());
            	    						}
            	    						set(
            	    							current,
            	    							"csOptions",
            	    							lv_csOptions_3_0,
            	    							"de.sos.MORA.CSharpOptions");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalMORA.g:312:4: ( (lv_cppOptions_4_0= ruleCppOptions ) )
            	    {
            	    // InternalMORA.g:312:4: ( (lv_cppOptions_4_0= ruleCppOptions ) )
            	    // InternalMORA.g:313:5: (lv_cppOptions_4_0= ruleCppOptions )
            	    {
            	    // InternalMORA.g:313:5: (lv_cppOptions_4_0= ruleCppOptions )
            	    // InternalMORA.g:314:6: lv_cppOptions_4_0= ruleCppOptions
            	    {

            	    						newCompositeNode(grammarAccess.getOptionsAccess().getCppOptionsCppOptionsParserRuleCall_2_2_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_cppOptions_4_0=ruleCppOptions();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOptionsRule());
            	    						}
            	    						set(
            	    							current,
            	    							"cppOptions",
            	    							lv_cppOptions_4_0,
            	    							"de.sos.MORA.CppOptions");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getOptionsAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptions"


    // $ANTLR start "entryRuleJavaOptions"
    // InternalMORA.g:340:1: entryRuleJavaOptions returns [EObject current=null] : iv_ruleJavaOptions= ruleJavaOptions EOF ;
    public final EObject entryRuleJavaOptions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJavaOptions = null;


        try {
            // InternalMORA.g:340:52: (iv_ruleJavaOptions= ruleJavaOptions EOF )
            // InternalMORA.g:341:2: iv_ruleJavaOptions= ruleJavaOptions EOF
            {
             newCompositeNode(grammarAccess.getJavaOptionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleJavaOptions=ruleJavaOptions();

            state._fsp--;

             current =iv_ruleJavaOptions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleJavaOptions"


    // $ANTLR start "ruleJavaOptions"
    // InternalMORA.g:347:1: ruleJavaOptions returns [EObject current=null] : (otherlv_0= 'java' otherlv_1= '{' otherlv_2= 'base-package' otherlv_3= '=' ( (lv_basePackage_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) ;
    public final EObject ruleJavaOptions() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_basePackage_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:353:2: ( (otherlv_0= 'java' otherlv_1= '{' otherlv_2= 'base-package' otherlv_3= '=' ( (lv_basePackage_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) )
            // InternalMORA.g:354:2: (otherlv_0= 'java' otherlv_1= '{' otherlv_2= 'base-package' otherlv_3= '=' ( (lv_basePackage_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            {
            // InternalMORA.g:354:2: (otherlv_0= 'java' otherlv_1= '{' otherlv_2= 'base-package' otherlv_3= '=' ( (lv_basePackage_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            // InternalMORA.g:355:3: otherlv_0= 'java' otherlv_1= '{' otherlv_2= 'base-package' otherlv_3= '=' ( (lv_basePackage_4_0= ruleQualifiedName ) ) otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getJavaOptionsAccess().getJavaKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_13); 

            			newLeafNode(otherlv_1, grammarAccess.getJavaOptionsAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,18,FOLLOW_14); 

            			newLeafNode(otherlv_2, grammarAccess.getJavaOptionsAccess().getBasePackageKeyword_2());
            		
            otherlv_3=(Token)match(input,19,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getJavaOptionsAccess().getEqualsSignKeyword_3());
            		
            // InternalMORA.g:371:3: ( (lv_basePackage_4_0= ruleQualifiedName ) )
            // InternalMORA.g:372:4: (lv_basePackage_4_0= ruleQualifiedName )
            {
            // InternalMORA.g:372:4: (lv_basePackage_4_0= ruleQualifiedName )
            // InternalMORA.g:373:5: lv_basePackage_4_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getJavaOptionsAccess().getBasePackageQualifiedNameParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_15);
            lv_basePackage_4_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getJavaOptionsRule());
            					}
            					set(
            						current,
            						"basePackage",
            						lv_basePackage_4_0,
            						"de.sos.MORA.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getJavaOptionsAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJavaOptions"


    // $ANTLR start "entryRuleCSharpOptions"
    // InternalMORA.g:398:1: entryRuleCSharpOptions returns [EObject current=null] : iv_ruleCSharpOptions= ruleCSharpOptions EOF ;
    public final EObject entryRuleCSharpOptions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCSharpOptions = null;


        try {
            // InternalMORA.g:398:54: (iv_ruleCSharpOptions= ruleCSharpOptions EOF )
            // InternalMORA.g:399:2: iv_ruleCSharpOptions= ruleCSharpOptions EOF
            {
             newCompositeNode(grammarAccess.getCSharpOptionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCSharpOptions=ruleCSharpOptions();

            state._fsp--;

             current =iv_ruleCSharpOptions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCSharpOptions"


    // $ANTLR start "ruleCSharpOptions"
    // InternalMORA.g:405:1: ruleCSharpOptions returns [EObject current=null] : (otherlv_0= 'csharp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) ;
    public final EObject ruleCSharpOptions() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_baseNamespace_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:411:2: ( (otherlv_0= 'csharp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) )
            // InternalMORA.g:412:2: (otherlv_0= 'csharp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            {
            // InternalMORA.g:412:2: (otherlv_0= 'csharp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            // InternalMORA.g:413:3: otherlv_0= 'csharp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getCSharpOptionsAccess().getCsharpKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_16); 

            			newLeafNode(otherlv_1, grammarAccess.getCSharpOptionsAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,21,FOLLOW_14); 

            			newLeafNode(otherlv_2, grammarAccess.getCSharpOptionsAccess().getBaseNamespaceKeyword_2());
            		
            otherlv_3=(Token)match(input,19,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getCSharpOptionsAccess().getEqualsSignKeyword_3());
            		
            // InternalMORA.g:429:3: ( (lv_baseNamespace_4_0= ruleQualifiedName ) )
            // InternalMORA.g:430:4: (lv_baseNamespace_4_0= ruleQualifiedName )
            {
            // InternalMORA.g:430:4: (lv_baseNamespace_4_0= ruleQualifiedName )
            // InternalMORA.g:431:5: lv_baseNamespace_4_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getCSharpOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_15);
            lv_baseNamespace_4_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCSharpOptionsRule());
            					}
            					set(
            						current,
            						"baseNamespace",
            						lv_baseNamespace_4_0,
            						"de.sos.MORA.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getCSharpOptionsAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCSharpOptions"


    // $ANTLR start "entryRuleCppOptions"
    // InternalMORA.g:456:1: entryRuleCppOptions returns [EObject current=null] : iv_ruleCppOptions= ruleCppOptions EOF ;
    public final EObject entryRuleCppOptions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCppOptions = null;


        try {
            // InternalMORA.g:456:51: (iv_ruleCppOptions= ruleCppOptions EOF )
            // InternalMORA.g:457:2: iv_ruleCppOptions= ruleCppOptions EOF
            {
             newCompositeNode(grammarAccess.getCppOptionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCppOptions=ruleCppOptions();

            state._fsp--;

             current =iv_ruleCppOptions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCppOptions"


    // $ANTLR start "ruleCppOptions"
    // InternalMORA.g:463:1: ruleCppOptions returns [EObject current=null] : (otherlv_0= 'cpp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) ;
    public final EObject ruleCppOptions() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_baseNamespace_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:469:2: ( (otherlv_0= 'cpp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' ) )
            // InternalMORA.g:470:2: (otherlv_0= 'cpp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            {
            // InternalMORA.g:470:2: (otherlv_0= 'cpp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}' )
            // InternalMORA.g:471:3: otherlv_0= 'cpp' otherlv_1= '{' otherlv_2= 'base-namespace' otherlv_3= '=' ( (lv_baseNamespace_4_0= ruleQualifiedName ) ) otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,22,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getCppOptionsAccess().getCppKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_16); 

            			newLeafNode(otherlv_1, grammarAccess.getCppOptionsAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,21,FOLLOW_14); 

            			newLeafNode(otherlv_2, grammarAccess.getCppOptionsAccess().getBaseNamespaceKeyword_2());
            		
            otherlv_3=(Token)match(input,19,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getCppOptionsAccess().getEqualsSignKeyword_3());
            		
            // InternalMORA.g:487:3: ( (lv_baseNamespace_4_0= ruleQualifiedName ) )
            // InternalMORA.g:488:4: (lv_baseNamespace_4_0= ruleQualifiedName )
            {
            // InternalMORA.g:488:4: (lv_baseNamespace_4_0= ruleQualifiedName )
            // InternalMORA.g:489:5: lv_baseNamespace_4_0= ruleQualifiedName
            {

            					newCompositeNode(grammarAccess.getCppOptionsAccess().getBaseNamespaceQualifiedNameParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_15);
            lv_baseNamespace_4_0=ruleQualifiedName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCppOptionsRule());
            					}
            					set(
            						current,
            						"baseNamespace",
            						lv_baseNamespace_4_0,
            						"de.sos.MORA.QualifiedName");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getCppOptionsAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCppOptions"


    // $ANTLR start "entryRuleTypeDecl"
    // InternalMORA.g:514:1: entryRuleTypeDecl returns [EObject current=null] : iv_ruleTypeDecl= ruleTypeDecl EOF ;
    public final EObject entryRuleTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeDecl = null;


        try {
            // InternalMORA.g:514:49: (iv_ruleTypeDecl= ruleTypeDecl EOF )
            // InternalMORA.g:515:2: iv_ruleTypeDecl= ruleTypeDecl EOF
            {
             newCompositeNode(grammarAccess.getTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTypeDecl=ruleTypeDecl();

            state._fsp--;

             current =iv_ruleTypeDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTypeDecl"


    // $ANTLR start "ruleTypeDecl"
    // InternalMORA.g:521:1: ruleTypeDecl returns [EObject current=null] : (this_SingleTypeDecl_0= ruleSingleTypeDecl | this_ListTypeDecl_1= ruleListTypeDecl ) ;
    public final EObject ruleTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject this_SingleTypeDecl_0 = null;

        EObject this_ListTypeDecl_1 = null;



        	enterRule();

        try {
            // InternalMORA.g:527:2: ( (this_SingleTypeDecl_0= ruleSingleTypeDecl | this_ListTypeDecl_1= ruleListTypeDecl ) )
            // InternalMORA.g:528:2: (this_SingleTypeDecl_0= ruleSingleTypeDecl | this_ListTypeDecl_1= ruleListTypeDecl )
            {
            // InternalMORA.g:528:2: (this_SingleTypeDecl_0= ruleSingleTypeDecl | this_ListTypeDecl_1= ruleListTypeDecl )
            int alt8=2;
            switch ( input.LA(1) ) {
            case 23:
            case 24:
            case 25:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                {
                alt8=1;
                }
                break;
            case RULE_ML_COMMENT:
                {
                int LA8_2 = input.LA(2);

                if ( ((LA8_2>=23 && LA8_2<=25)) ) {
                    alt8=1;
                }
                else if ( (LA8_2==26) ) {
                    alt8=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
                }
                break;
            case 26:
                {
                alt8=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalMORA.g:529:3: this_SingleTypeDecl_0= ruleSingleTypeDecl
                    {

                    			newCompositeNode(grammarAccess.getTypeDeclAccess().getSingleTypeDeclParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SingleTypeDecl_0=ruleSingleTypeDecl();

                    state._fsp--;


                    			current = this_SingleTypeDecl_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMORA.g:538:3: this_ListTypeDecl_1= ruleListTypeDecl
                    {

                    			newCompositeNode(grammarAccess.getTypeDeclAccess().getListTypeDeclParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ListTypeDecl_1=ruleListTypeDecl();

                    state._fsp--;


                    			current = this_ListTypeDecl_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTypeDecl"


    // $ANTLR start "entryRuleSingleTypeDecl"
    // InternalMORA.g:550:1: entryRuleSingleTypeDecl returns [EObject current=null] : iv_ruleSingleTypeDecl= ruleSingleTypeDecl EOF ;
    public final EObject entryRuleSingleTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleTypeDecl = null;


        try {
            // InternalMORA.g:550:55: (iv_ruleSingleTypeDecl= ruleSingleTypeDecl EOF )
            // InternalMORA.g:551:2: iv_ruleSingleTypeDecl= ruleSingleTypeDecl EOF
            {
             newCompositeNode(grammarAccess.getSingleTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSingleTypeDecl=ruleSingleTypeDecl();

            state._fsp--;

             current =iv_ruleSingleTypeDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSingleTypeDecl"


    // $ANTLR start "ruleSingleTypeDecl"
    // InternalMORA.g:557:1: ruleSingleTypeDecl returns [EObject current=null] : (this_PrimTypeDecl_0= rulePrimTypeDecl | this_StructDecl_1= ruleStructDecl | this_EnumDecl_2= ruleEnumDecl ) ;
    public final EObject ruleSingleTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject this_PrimTypeDecl_0 = null;

        EObject this_StructDecl_1 = null;

        EObject this_EnumDecl_2 = null;



        	enterRule();

        try {
            // InternalMORA.g:563:2: ( (this_PrimTypeDecl_0= rulePrimTypeDecl | this_StructDecl_1= ruleStructDecl | this_EnumDecl_2= ruleEnumDecl ) )
            // InternalMORA.g:564:2: (this_PrimTypeDecl_0= rulePrimTypeDecl | this_StructDecl_1= ruleStructDecl | this_EnumDecl_2= ruleEnumDecl )
            {
            // InternalMORA.g:564:2: (this_PrimTypeDecl_0= rulePrimTypeDecl | this_StructDecl_1= ruleStructDecl | this_EnumDecl_2= ruleEnumDecl )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                {
                alt9=1;
                }
                break;
            case RULE_ML_COMMENT:
                {
                int LA9_2 = input.LA(2);

                if ( ((LA9_2>=23 && LA9_2<=24)) ) {
                    alt9=2;
                }
                else if ( (LA9_2==25) ) {
                    alt9=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 2, input);

                    throw nvae;
                }
                }
                break;
            case 23:
            case 24:
                {
                alt9=2;
                }
                break;
            case 25:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalMORA.g:565:3: this_PrimTypeDecl_0= rulePrimTypeDecl
                    {

                    			newCompositeNode(grammarAccess.getSingleTypeDeclAccess().getPrimTypeDeclParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_PrimTypeDecl_0=rulePrimTypeDecl();

                    state._fsp--;


                    			current = this_PrimTypeDecl_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMORA.g:574:3: this_StructDecl_1= ruleStructDecl
                    {

                    			newCompositeNode(grammarAccess.getSingleTypeDeclAccess().getStructDeclParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_StructDecl_1=ruleStructDecl();

                    state._fsp--;


                    			current = this_StructDecl_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMORA.g:583:3: this_EnumDecl_2= ruleEnumDecl
                    {

                    			newCompositeNode(grammarAccess.getSingleTypeDeclAccess().getEnumDeclParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_EnumDecl_2=ruleEnumDecl();

                    state._fsp--;


                    			current = this_EnumDecl_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSingleTypeDecl"


    // $ANTLR start "entryRulePrimTypeDecl"
    // InternalMORA.g:595:1: entryRulePrimTypeDecl returns [EObject current=null] : iv_rulePrimTypeDecl= rulePrimTypeDecl EOF ;
    public final EObject entryRulePrimTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimTypeDecl = null;


        try {
            // InternalMORA.g:595:53: (iv_rulePrimTypeDecl= rulePrimTypeDecl EOF )
            // InternalMORA.g:596:2: iv_rulePrimTypeDecl= rulePrimTypeDecl EOF
            {
             newCompositeNode(grammarAccess.getPrimTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimTypeDecl=rulePrimTypeDecl();

            state._fsp--;

             current =iv_rulePrimTypeDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimTypeDecl"


    // $ANTLR start "rulePrimTypeDecl"
    // InternalMORA.g:602:1: rulePrimTypeDecl returns [EObject current=null] : ( (lv_name_0_0= rulePrimTypeLiteral ) ) ;
    public final EObject rulePrimTypeDecl() throws RecognitionException {
        EObject current = null;

        Enumerator lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:608:2: ( ( (lv_name_0_0= rulePrimTypeLiteral ) ) )
            // InternalMORA.g:609:2: ( (lv_name_0_0= rulePrimTypeLiteral ) )
            {
            // InternalMORA.g:609:2: ( (lv_name_0_0= rulePrimTypeLiteral ) )
            // InternalMORA.g:610:3: (lv_name_0_0= rulePrimTypeLiteral )
            {
            // InternalMORA.g:610:3: (lv_name_0_0= rulePrimTypeLiteral )
            // InternalMORA.g:611:4: lv_name_0_0= rulePrimTypeLiteral
            {

            				newCompositeNode(grammarAccess.getPrimTypeDeclAccess().getNamePrimTypeLiteralEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_name_0_0=rulePrimTypeLiteral();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getPrimTypeDeclRule());
            				}
            				set(
            					current,
            					"name",
            					lv_name_0_0,
            					"de.sos.MORA.PrimTypeLiteral");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimTypeDecl"


    // $ANTLR start "entryRuleAnnotation"
    // InternalMORA.g:631:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // InternalMORA.g:631:51: (iv_ruleAnnotation= ruleAnnotation EOF )
            // InternalMORA.g:632:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
             newCompositeNode(grammarAccess.getAnnotationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnnotation=ruleAnnotation();

            state._fsp--;

             current =iv_ruleAnnotation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // InternalMORA.g:638:1: ruleAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;


        	enterRule();

        try {
            // InternalMORA.g:644:2: ( (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )? ) )
            // InternalMORA.g:645:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )? )
            {
            // InternalMORA.g:645:2: (otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )? )
            // InternalMORA.g:646:3: otherlv_0= '@' ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )?
            {
            otherlv_0=(Token)match(input,23,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getAnnotationAccess().getCommercialAtKeyword_0());
            		
            // InternalMORA.g:650:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMORA.g:651:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMORA.g:651:4: (lv_name_1_0= RULE_ID )
            // InternalMORA.g:652:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_17); 

            					newLeafNode(lv_name_1_0, grammarAccess.getAnnotationAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAnnotationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            // InternalMORA.g:668:3: (otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalMORA.g:669:4: otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_9); 

                    				newLeafNode(otherlv_2, grammarAccess.getAnnotationAccess().getEqualsSignKeyword_2_0());
                    			
                    // InternalMORA.g:673:4: ( (lv_value_3_0= RULE_STRING ) )
                    // InternalMORA.g:674:5: (lv_value_3_0= RULE_STRING )
                    {
                    // InternalMORA.g:674:5: (lv_value_3_0= RULE_STRING )
                    // InternalMORA.g:675:6: lv_value_3_0= RULE_STRING
                    {
                    lv_value_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_3_0, grammarAccess.getAnnotationAccess().getValueSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAnnotationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"de.sos.MORA.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleStructDecl"
    // InternalMORA.g:696:1: entryRuleStructDecl returns [EObject current=null] : iv_ruleStructDecl= ruleStructDecl EOF ;
    public final EObject entryRuleStructDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStructDecl = null;


        try {
            // InternalMORA.g:696:51: (iv_ruleStructDecl= ruleStructDecl EOF )
            // InternalMORA.g:697:2: iv_ruleStructDecl= ruleStructDecl EOF
            {
             newCompositeNode(grammarAccess.getStructDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStructDecl=ruleStructDecl();

            state._fsp--;

             current =iv_ruleStructDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStructDecl"


    // $ANTLR start "ruleStructDecl"
    // InternalMORA.g:703:1: ruleStructDecl returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'struct' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )* otherlv_7= '}' ) ;
    public final EObject ruleStructDecl() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_anno_1_0 = null;

        EObject lv_member_5_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:709:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'struct' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )* otherlv_7= '}' ) )
            // InternalMORA.g:710:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'struct' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )* otherlv_7= '}' )
            {
            // InternalMORA.g:710:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'struct' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )* otherlv_7= '}' )
            // InternalMORA.g:711:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'struct' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )* otherlv_7= '}'
            {
            // InternalMORA.g:711:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ML_COMMENT) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMORA.g:712:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:712:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:713:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_18); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getStructDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStructDeclRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:729:3: ( (lv_anno_1_0= ruleAnnotation ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==23) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMORA.g:730:4: (lv_anno_1_0= ruleAnnotation )
            	    {
            	    // InternalMORA.g:730:4: (lv_anno_1_0= ruleAnnotation )
            	    // InternalMORA.g:731:5: lv_anno_1_0= ruleAnnotation
            	    {

            	    					newCompositeNode(grammarAccess.getStructDeclAccess().getAnnoAnnotationParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_18);
            	    lv_anno_1_0=ruleAnnotation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getStructDeclRule());
            	    					}
            	    					add(
            	    						current,
            	    						"anno",
            	    						lv_anno_1_0,
            	    						"de.sos.MORA.Annotation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            otherlv_2=(Token)match(input,24,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getStructDeclAccess().getStructKeyword_2());
            		
            // InternalMORA.g:752:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalMORA.g:753:4: (lv_name_3_0= RULE_ID )
            {
            // InternalMORA.g:753:4: (lv_name_3_0= RULE_ID )
            // InternalMORA.g:754:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_3_0, grammarAccess.getStructDeclAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStructDeclRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_3_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            otherlv_4=(Token)match(input,12,FOLLOW_19); 

            			newLeafNode(otherlv_4, grammarAccess.getStructDeclAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMORA.g:774:3: ( ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )? )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID||LA14_0==RULE_ML_COMMENT||LA14_0==23||(LA14_0>=39 && LA14_0<=47)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalMORA.g:775:4: ( (lv_member_5_0= ruleMember ) ) (otherlv_6= ';' )?
            	    {
            	    // InternalMORA.g:775:4: ( (lv_member_5_0= ruleMember ) )
            	    // InternalMORA.g:776:5: (lv_member_5_0= ruleMember )
            	    {
            	    // InternalMORA.g:776:5: (lv_member_5_0= ruleMember )
            	    // InternalMORA.g:777:6: lv_member_5_0= ruleMember
            	    {

            	    						newCompositeNode(grammarAccess.getStructDeclAccess().getMemberMemberParserRuleCall_5_0_0());
            	    					
            	    pushFollow(FOLLOW_20);
            	    lv_member_5_0=ruleMember();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getStructDeclRule());
            	    						}
            	    						add(
            	    							current,
            	    							"member",
            	    							lv_member_5_0,
            	    							"de.sos.MORA.Member");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalMORA.g:794:4: (otherlv_6= ';' )?
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==13) ) {
            	        alt13=1;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // InternalMORA.g:795:5: otherlv_6= ';'
            	            {
            	            otherlv_6=(Token)match(input,13,FOLLOW_19); 

            	            					newLeafNode(otherlv_6, grammarAccess.getStructDeclAccess().getSemicolonKeyword_5_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            otherlv_7=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getStructDeclAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStructDecl"


    // $ANTLR start "entryRuleMember"
    // InternalMORA.g:809:1: entryRuleMember returns [EObject current=null] : iv_ruleMember= ruleMember EOF ;
    public final EObject entryRuleMember() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMember = null;


        try {
            // InternalMORA.g:809:47: (iv_ruleMember= ruleMember EOF )
            // InternalMORA.g:810:2: iv_ruleMember= ruleMember EOF
            {
             newCompositeNode(grammarAccess.getMemberRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMember=ruleMember();

            state._fsp--;

             current =iv_ruleMember; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMember"


    // $ANTLR start "ruleMember"
    // InternalMORA.g:816:1: ruleMember returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) ) ( (lv_name_4_0= RULE_ID ) ) ) ;
    public final EObject ruleMember() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token lv_name_4_0=null;
        EObject lv_anno_1_0 = null;

        Enumerator lv_primType_3_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:822:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) ) ( (lv_name_4_0= RULE_ID ) ) ) )
            // InternalMORA.g:823:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) ) ( (lv_name_4_0= RULE_ID ) ) )
            {
            // InternalMORA.g:823:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) ) ( (lv_name_4_0= RULE_ID ) ) )
            // InternalMORA.g:824:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) ) ( (lv_name_4_0= RULE_ID ) )
            {
            // InternalMORA.g:824:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ML_COMMENT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMORA.g:825:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:825:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:826:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_21); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getMemberAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getMemberRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:842:3: ( (lv_anno_1_0= ruleAnnotation ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==23) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalMORA.g:843:4: (lv_anno_1_0= ruleAnnotation )
            	    {
            	    // InternalMORA.g:843:4: (lv_anno_1_0= ruleAnnotation )
            	    // InternalMORA.g:844:5: lv_anno_1_0= ruleAnnotation
            	    {

            	    					newCompositeNode(grammarAccess.getMemberAccess().getAnnoAnnotationParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_21);
            	    lv_anno_1_0=ruleAnnotation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMemberRule());
            	    					}
            	    					add(
            	    						current,
            	    						"anno",
            	    						lv_anno_1_0,
            	    						"de.sos.MORA.Annotation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // InternalMORA.g:861:3: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_3_0= rulePrimTypeLiteral ) ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                alt17=1;
            }
            else if ( ((LA17_0>=39 && LA17_0<=47)) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // InternalMORA.g:862:4: ( ( ruleQualifiedName ) )
                    {
                    // InternalMORA.g:862:4: ( ( ruleQualifiedName ) )
                    // InternalMORA.g:863:5: ( ruleQualifiedName )
                    {
                    // InternalMORA.g:863:5: ( ruleQualifiedName )
                    // InternalMORA.g:864:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMemberRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getMemberAccess().getComplexTypeTypeDeclCrossReference_2_0_0());
                    					
                    pushFollow(FOLLOW_5);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:879:4: ( (lv_primType_3_0= rulePrimTypeLiteral ) )
                    {
                    // InternalMORA.g:879:4: ( (lv_primType_3_0= rulePrimTypeLiteral ) )
                    // InternalMORA.g:880:5: (lv_primType_3_0= rulePrimTypeLiteral )
                    {
                    // InternalMORA.g:880:5: (lv_primType_3_0= rulePrimTypeLiteral )
                    // InternalMORA.g:881:6: lv_primType_3_0= rulePrimTypeLiteral
                    {

                    						newCompositeNode(grammarAccess.getMemberAccess().getPrimTypePrimTypeLiteralEnumRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_5);
                    lv_primType_3_0=rulePrimTypeLiteral();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMemberRule());
                    						}
                    						set(
                    							current,
                    							"primType",
                    							lv_primType_3_0,
                    							"de.sos.MORA.PrimTypeLiteral");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalMORA.g:899:3: ( (lv_name_4_0= RULE_ID ) )
            // InternalMORA.g:900:4: (lv_name_4_0= RULE_ID )
            {
            // InternalMORA.g:900:4: (lv_name_4_0= RULE_ID )
            // InternalMORA.g:901:5: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_4_0, grammarAccess.getMemberAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMemberRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"de.sos.MORA.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMember"


    // $ANTLR start "entryRuleEnumDecl"
    // InternalMORA.g:921:1: entryRuleEnumDecl returns [EObject current=null] : iv_ruleEnumDecl= ruleEnumDecl EOF ;
    public final EObject entryRuleEnumDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumDecl = null;


        try {
            // InternalMORA.g:921:49: (iv_ruleEnumDecl= ruleEnumDecl EOF )
            // InternalMORA.g:922:2: iv_ruleEnumDecl= ruleEnumDecl EOF
            {
             newCompositeNode(grammarAccess.getEnumDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumDecl=ruleEnumDecl();

            state._fsp--;

             current =iv_ruleEnumDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumDecl"


    // $ANTLR start "ruleEnumDecl"
    // InternalMORA.g:928:1: ruleEnumDecl returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'enum' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+ otherlv_6= '}' ) ;
    public final EObject ruleEnumDecl() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_literals_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:934:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'enum' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+ otherlv_6= '}' ) )
            // InternalMORA.g:935:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'enum' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+ otherlv_6= '}' )
            {
            // InternalMORA.g:935:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'enum' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+ otherlv_6= '}' )
            // InternalMORA.g:936:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'enum' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+ otherlv_6= '}'
            {
            // InternalMORA.g:936:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ML_COMMENT) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalMORA.g:937:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:937:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:938:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_22); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getEnumDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getEnumDeclRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,25,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getEnumDeclAccess().getEnumKeyword_1());
            		
            // InternalMORA.g:958:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalMORA.g:959:4: (lv_name_2_0= RULE_ID )
            {
            // InternalMORA.g:959:4: (lv_name_2_0= RULE_ID )
            // InternalMORA.g:960:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEnumDeclAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEnumDeclRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_23); 

            			newLeafNode(otherlv_3, grammarAccess.getEnumDeclAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalMORA.g:980:3: ( ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )? )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID||LA20_0==RULE_ML_COMMENT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMORA.g:981:4: ( (lv_literals_4_0= ruleLiteral ) ) (otherlv_5= ';' )?
            	    {
            	    // InternalMORA.g:981:4: ( (lv_literals_4_0= ruleLiteral ) )
            	    // InternalMORA.g:982:5: (lv_literals_4_0= ruleLiteral )
            	    {
            	    // InternalMORA.g:982:5: (lv_literals_4_0= ruleLiteral )
            	    // InternalMORA.g:983:6: lv_literals_4_0= ruleLiteral
            	    {

            	    						newCompositeNode(grammarAccess.getEnumDeclAccess().getLiteralsLiteralParserRuleCall_4_0_0());
            	    					
            	    pushFollow(FOLLOW_24);
            	    lv_literals_4_0=ruleLiteral();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEnumDeclRule());
            	    						}
            	    						add(
            	    							current,
            	    							"literals",
            	    							lv_literals_4_0,
            	    							"de.sos.MORA.Literal");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalMORA.g:1000:4: (otherlv_5= ';' )?
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==13) ) {
            	        alt19=1;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // InternalMORA.g:1001:5: otherlv_5= ';'
            	            {
            	            otherlv_5=(Token)match(input,13,FOLLOW_25); 

            	            					newLeafNode(otherlv_5, grammarAccess.getEnumDeclAccess().getSemicolonKeyword_4_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            otherlv_6=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getEnumDeclAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumDecl"


    // $ANTLR start "entryRuleLiteral"
    // InternalMORA.g:1015:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // InternalMORA.g:1015:48: (iv_ruleLiteral= ruleLiteral EOF )
            // InternalMORA.g:1016:2: iv_ruleLiteral= ruleLiteral EOF
            {
             newCompositeNode(grammarAccess.getLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;

             current =iv_ruleLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalMORA.g:1022:1: ruleLiteral returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )? ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;


        	enterRule();

        try {
            // InternalMORA.g:1028:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )? ) )
            // InternalMORA.g:1029:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )? )
            {
            // InternalMORA.g:1029:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )? )
            // InternalMORA.g:1030:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_name_1_0= RULE_ID ) ) (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )?
            {
            // InternalMORA.g:1030:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_ML_COMMENT) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalMORA.g:1031:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:1031:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:1032:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_5); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getLiteralAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getLiteralRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:1048:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMORA.g:1049:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMORA.g:1049:4: (lv_name_1_0= RULE_ID )
            // InternalMORA.g:1050:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_17); 

            					newLeafNode(lv_name_1_0, grammarAccess.getLiteralAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLiteralRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            // InternalMORA.g:1066:3: (otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==19) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalMORA.g:1067:4: otherlv_2= '=' ( (lv_value_3_0= RULE_INT ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_26); 

                    				newLeafNode(otherlv_2, grammarAccess.getLiteralAccess().getEqualsSignKeyword_2_0());
                    			
                    // InternalMORA.g:1071:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalMORA.g:1072:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalMORA.g:1072:5: (lv_value_3_0= RULE_INT )
                    // InternalMORA.g:1073:6: lv_value_3_0= RULE_INT
                    {
                    lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_value_3_0, grammarAccess.getLiteralAccess().getValueINTTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLiteralRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"de.sos.MORA.INT");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleListTypeDecl"
    // InternalMORA.g:1094:1: entryRuleListTypeDecl returns [EObject current=null] : iv_ruleListTypeDecl= ruleListTypeDecl EOF ;
    public final EObject entryRuleListTypeDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListTypeDecl = null;


        try {
            // InternalMORA.g:1094:53: (iv_ruleListTypeDecl= ruleListTypeDecl EOF )
            // InternalMORA.g:1095:2: iv_ruleListTypeDecl= ruleListTypeDecl EOF
            {
             newCompositeNode(grammarAccess.getListTypeDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleListTypeDecl=ruleListTypeDecl();

            state._fsp--;

             current =iv_ruleListTypeDecl; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleListTypeDecl"


    // $ANTLR start "ruleListTypeDecl"
    // InternalMORA.g:1101:1: ruleListTypeDecl returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'List' otherlv_2= '<' ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) otherlv_5= '>' ( (lv_name_6_0= RULE_ID ) ) ) ;
    public final EObject ruleListTypeDecl() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token lv_name_6_0=null;
        Enumerator lv_primType_4_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:1107:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'List' otherlv_2= '<' ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) otherlv_5= '>' ( (lv_name_6_0= RULE_ID ) ) ) )
            // InternalMORA.g:1108:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'List' otherlv_2= '<' ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) otherlv_5= '>' ( (lv_name_6_0= RULE_ID ) ) )
            {
            // InternalMORA.g:1108:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'List' otherlv_2= '<' ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) otherlv_5= '>' ( (lv_name_6_0= RULE_ID ) ) )
            // InternalMORA.g:1109:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? otherlv_1= 'List' otherlv_2= '<' ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) otherlv_5= '>' ( (lv_name_6_0= RULE_ID ) )
            {
            // InternalMORA.g:1109:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ML_COMMENT) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalMORA.g:1110:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:1110:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:1111:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_27); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getListTypeDeclAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getListTypeDeclRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,26,FOLLOW_28); 

            			newLeafNode(otherlv_1, grammarAccess.getListTypeDeclAccess().getListKeyword_1());
            		
            otherlv_2=(Token)match(input,27,FOLLOW_29); 

            			newLeafNode(otherlv_2, grammarAccess.getListTypeDeclAccess().getLessThanSignKeyword_2());
            		
            // InternalMORA.g:1135:3: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_ID) ) {
                alt24=1;
            }
            else if ( ((LA24_0>=39 && LA24_0<=47)) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalMORA.g:1136:4: ( ( ruleQualifiedName ) )
                    {
                    // InternalMORA.g:1136:4: ( ( ruleQualifiedName ) )
                    // InternalMORA.g:1137:5: ( ruleQualifiedName )
                    {
                    // InternalMORA.g:1137:5: ( ruleQualifiedName )
                    // InternalMORA.g:1138:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getListTypeDeclRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getListTypeDeclAccess().getValueTypeSingleTypeDeclCrossReference_3_0_0());
                    					
                    pushFollow(FOLLOW_30);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:1153:4: ( (lv_primType_4_0= rulePrimTypeLiteral ) )
                    {
                    // InternalMORA.g:1153:4: ( (lv_primType_4_0= rulePrimTypeLiteral ) )
                    // InternalMORA.g:1154:5: (lv_primType_4_0= rulePrimTypeLiteral )
                    {
                    // InternalMORA.g:1154:5: (lv_primType_4_0= rulePrimTypeLiteral )
                    // InternalMORA.g:1155:6: lv_primType_4_0= rulePrimTypeLiteral
                    {

                    						newCompositeNode(grammarAccess.getListTypeDeclAccess().getPrimTypePrimTypeLiteralEnumRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_primType_4_0=rulePrimTypeLiteral();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getListTypeDeclRule());
                    						}
                    						set(
                    							current,
                    							"primType",
                    							lv_primType_4_0,
                    							"de.sos.MORA.PrimTypeLiteral");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,28,FOLLOW_5); 

            			newLeafNode(otherlv_5, grammarAccess.getListTypeDeclAccess().getGreaterThanSignKeyword_4());
            		
            // InternalMORA.g:1177:3: ( (lv_name_6_0= RULE_ID ) )
            // InternalMORA.g:1178:4: (lv_name_6_0= RULE_ID )
            {
            // InternalMORA.g:1178:4: (lv_name_6_0= RULE_ID )
            // InternalMORA.g:1179:5: lv_name_6_0= RULE_ID
            {
            lv_name_6_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_6_0, grammarAccess.getListTypeDeclAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getListTypeDeclRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_6_0,
            						"de.sos.MORA.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleListTypeDecl"


    // $ANTLR start "entryRuleInterface"
    // InternalMORA.g:1199:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // InternalMORA.g:1199:50: (iv_ruleInterface= ruleInterface EOF )
            // InternalMORA.g:1200:2: iv_ruleInterface= ruleInterface EOF
            {
             newCompositeNode(grammarAccess.getInterfaceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInterface=ruleInterface();

            state._fsp--;

             current =iv_ruleInterface; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterface"


    // $ANTLR start "ruleInterface"
    // InternalMORA.g:1206:1: ruleInterface returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'interface' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_8= '{' ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )* otherlv_11= '}' ) ;
    public final EObject ruleInterface() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_anno_1_0 = null;

        EObject lv_methods_9_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:1212:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'interface' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_8= '{' ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )* otherlv_11= '}' ) )
            // InternalMORA.g:1213:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'interface' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_8= '{' ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )* otherlv_11= '}' )
            {
            // InternalMORA.g:1213:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'interface' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_8= '{' ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )* otherlv_11= '}' )
            // InternalMORA.g:1214:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( (lv_anno_1_0= ruleAnnotation ) )* otherlv_2= 'interface' ( (lv_name_3_0= RULE_ID ) ) (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? otherlv_8= '{' ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )* otherlv_11= '}'
            {
            // InternalMORA.g:1214:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_ML_COMMENT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalMORA.g:1215:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:1215:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:1216:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_31); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getInterfaceAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getInterfaceRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:1232:3: ( (lv_anno_1_0= ruleAnnotation ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==23) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalMORA.g:1233:4: (lv_anno_1_0= ruleAnnotation )
            	    {
            	    // InternalMORA.g:1233:4: (lv_anno_1_0= ruleAnnotation )
            	    // InternalMORA.g:1234:5: lv_anno_1_0= ruleAnnotation
            	    {

            	    					newCompositeNode(grammarAccess.getInterfaceAccess().getAnnoAnnotationParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_31);
            	    lv_anno_1_0=ruleAnnotation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"anno",
            	    						lv_anno_1_0,
            	    						"de.sos.MORA.Annotation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            otherlv_2=(Token)match(input,29,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getInterfaceAccess().getInterfaceKeyword_2());
            		
            // InternalMORA.g:1255:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalMORA.g:1256:4: (lv_name_3_0= RULE_ID )
            {
            // InternalMORA.g:1256:4: (lv_name_3_0= RULE_ID )
            // InternalMORA.g:1257:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_32); 

            					newLeafNode(lv_name_3_0, grammarAccess.getInterfaceAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInterfaceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_3_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            // InternalMORA.g:1273:3: (otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==30) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalMORA.g:1274:4: otherlv_4= 'extends' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,30,FOLLOW_5); 

                    				newLeafNode(otherlv_4, grammarAccess.getInterfaceAccess().getExtendsKeyword_4_0());
                    			
                    // InternalMORA.g:1278:4: ( ( ruleQualifiedName ) )
                    // InternalMORA.g:1279:5: ( ruleQualifiedName )
                    {
                    // InternalMORA.g:1279:5: ( ruleQualifiedName )
                    // InternalMORA.g:1280:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getInterfaceRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_1_0());
                    					
                    pushFollow(FOLLOW_33);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMORA.g:1294:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==31) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalMORA.g:1295:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,31,FOLLOW_5); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getInterfaceAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalMORA.g:1299:5: ( ( ruleQualifiedName ) )
                    	    // InternalMORA.g:1300:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalMORA.g:1300:6: ( ruleQualifiedName )
                    	    // InternalMORA.g:1301:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getInterfaceRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getInterfaceAccess().getParentsInterfaceCrossReference_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_33);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,12,FOLLOW_34); 

            			newLeafNode(otherlv_8, grammarAccess.getInterfaceAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalMORA.g:1321:3: ( ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )? )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==RULE_ML_COMMENT||(LA30_0>=39 && LA30_0<=47)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalMORA.g:1322:4: ( (lv_methods_9_0= ruleMethod ) ) (otherlv_10= ';' )?
            	    {
            	    // InternalMORA.g:1322:4: ( (lv_methods_9_0= ruleMethod ) )
            	    // InternalMORA.g:1323:5: (lv_methods_9_0= ruleMethod )
            	    {
            	    // InternalMORA.g:1323:5: (lv_methods_9_0= ruleMethod )
            	    // InternalMORA.g:1324:6: lv_methods_9_0= ruleMethod
            	    {

            	    						newCompositeNode(grammarAccess.getInterfaceAccess().getMethodsMethodParserRuleCall_6_0_0());
            	    					
            	    pushFollow(FOLLOW_35);
            	    lv_methods_9_0=ruleMethod();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	    						}
            	    						add(
            	    							current,
            	    							"methods",
            	    							lv_methods_9_0,
            	    							"de.sos.MORA.Method");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalMORA.g:1341:4: (otherlv_10= ';' )?
            	    int alt29=2;
            	    int LA29_0 = input.LA(1);

            	    if ( (LA29_0==13) ) {
            	        alt29=1;
            	    }
            	    switch (alt29) {
            	        case 1 :
            	            // InternalMORA.g:1342:5: otherlv_10= ';'
            	            {
            	            otherlv_10=(Token)match(input,13,FOLLOW_34); 

            	            					newLeafNode(otherlv_10, grammarAccess.getInterfaceAccess().getSemicolonKeyword_6_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            otherlv_11=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getInterfaceAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInterface"


    // $ANTLR start "entryRuleMethod"
    // InternalMORA.g:1356:1: entryRuleMethod returns [EObject current=null] : iv_ruleMethod= ruleMethod EOF ;
    public final EObject entryRuleMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethod = null;


        try {
            // InternalMORA.g:1356:47: (iv_ruleMethod= ruleMethod EOF )
            // InternalMORA.g:1357:2: iv_ruleMethod= ruleMethod EOF
            {
             newCompositeNode(grammarAccess.getMethodRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMethod=ruleMethod();

            state._fsp--;

             current =iv_ruleMethod; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // InternalMORA.g:1363:1: ruleMethod returns [EObject current=null] : ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= '(' ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )? otherlv_10= ')' (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )? ) ;
    public final EObject ruleMethod() throws RecognitionException {
        EObject current = null;

        Token lv_doc_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Enumerator lv_primType_4_0 = null;

        EObject lv_parameters_7_0 = null;

        EObject lv_parameters_9_0 = null;

        EObject lv_exceptions_12_0 = null;

        EObject lv_exceptions_14_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:1369:2: ( ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= '(' ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )? otherlv_10= ')' (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )? ) )
            // InternalMORA.g:1370:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= '(' ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )? otherlv_10= ')' (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )? )
            {
            // InternalMORA.g:1370:2: ( ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= '(' ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )? otherlv_10= ')' (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )? )
            // InternalMORA.g:1371:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )? ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= '(' ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )? otherlv_10= ')' (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )?
            {
            // InternalMORA.g:1371:3: ( (lv_doc_0_0= RULE_ML_COMMENT ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ML_COMMENT) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalMORA.g:1372:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    {
                    // InternalMORA.g:1372:4: (lv_doc_0_0= RULE_ML_COMMENT )
                    // InternalMORA.g:1373:5: lv_doc_0_0= RULE_ML_COMMENT
                    {
                    lv_doc_0_0=(Token)match(input,RULE_ML_COMMENT,FOLLOW_29); 

                    					newLeafNode(lv_doc_0_0, grammarAccess.getMethodAccess().getDocML_COMMENTTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getMethodRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"doc",
                    						lv_doc_0_0,
                    						"de.sos.MORA.ML_COMMENT");
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:1389:3: ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' ) | ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==RULE_ID) ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1==RULE_ID||(LA33_1>=37 && LA33_1<=38)) ) {
                    alt33=2;
                }
                else if ( (LA33_1==32) ) {
                    alt33=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA33_0>=39 && LA33_0<=47)) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // InternalMORA.g:1390:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' )
                    {
                    // InternalMORA.g:1390:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '*' )
                    // InternalMORA.g:1391:5: ( (otherlv_1= RULE_ID ) ) otherlv_2= '*'
                    {
                    // InternalMORA.g:1391:5: ( (otherlv_1= RULE_ID ) )
                    // InternalMORA.g:1392:6: (otherlv_1= RULE_ID )
                    {
                    // InternalMORA.g:1392:6: (otherlv_1= RULE_ID )
                    // InternalMORA.g:1393:7: otherlv_1= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getMethodRule());
                    							}
                    						
                    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_36); 

                    							newLeafNode(otherlv_1, grammarAccess.getMethodAccess().getReturnProxyTypeInterfaceCrossReference_1_0_0_0());
                    						

                    }


                    }

                    otherlv_2=(Token)match(input,32,FOLLOW_5); 

                    					newLeafNode(otherlv_2, grammarAccess.getMethodAccess().getAsteriskKeyword_1_0_1());
                    				

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:1410:4: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) )
                    {
                    // InternalMORA.g:1410:4: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_4_0= rulePrimTypeLiteral ) ) )
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==RULE_ID) ) {
                        alt32=1;
                    }
                    else if ( ((LA32_0>=39 && LA32_0<=47)) ) {
                        alt32=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 32, 0, input);

                        throw nvae;
                    }
                    switch (alt32) {
                        case 1 :
                            // InternalMORA.g:1411:5: ( ( ruleQualifiedName ) )
                            {
                            // InternalMORA.g:1411:5: ( ( ruleQualifiedName ) )
                            // InternalMORA.g:1412:6: ( ruleQualifiedName )
                            {
                            // InternalMORA.g:1412:6: ( ruleQualifiedName )
                            // InternalMORA.g:1413:7: ruleQualifiedName
                            {

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getMethodRule());
                            							}
                            						

                            							newCompositeNode(grammarAccess.getMethodAccess().getComplexTypeTypeDeclCrossReference_1_1_0_0());
                            						
                            pushFollow(FOLLOW_5);
                            ruleQualifiedName();

                            state._fsp--;


                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalMORA.g:1428:5: ( (lv_primType_4_0= rulePrimTypeLiteral ) )
                            {
                            // InternalMORA.g:1428:5: ( (lv_primType_4_0= rulePrimTypeLiteral ) )
                            // InternalMORA.g:1429:6: (lv_primType_4_0= rulePrimTypeLiteral )
                            {
                            // InternalMORA.g:1429:6: (lv_primType_4_0= rulePrimTypeLiteral )
                            // InternalMORA.g:1430:7: lv_primType_4_0= rulePrimTypeLiteral
                            {

                            							newCompositeNode(grammarAccess.getMethodAccess().getPrimTypePrimTypeLiteralEnumRuleCall_1_1_1_0());
                            						
                            pushFollow(FOLLOW_5);
                            lv_primType_4_0=rulePrimTypeLiteral();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getMethodRule());
                            							}
                            							set(
                            								current,
                            								"primType",
                            								lv_primType_4_0,
                            								"de.sos.MORA.PrimTypeLiteral");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalMORA.g:1449:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalMORA.g:1450:4: (lv_name_5_0= RULE_ID )
            {
            // InternalMORA.g:1450:4: (lv_name_5_0= RULE_ID )
            // InternalMORA.g:1451:5: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_37); 

            					newLeafNode(lv_name_5_0, grammarAccess.getMethodAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMethodRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_5_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            otherlv_6=(Token)match(input,33,FOLLOW_38); 

            			newLeafNode(otherlv_6, grammarAccess.getMethodAccess().getLeftParenthesisKeyword_3());
            		
            // InternalMORA.g:1471:3: ( ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )* )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID||(LA35_0>=39 && LA35_0<=47)) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalMORA.g:1472:4: ( (lv_parameters_7_0= ruleParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )*
                    {
                    // InternalMORA.g:1472:4: ( (lv_parameters_7_0= ruleParameter ) )
                    // InternalMORA.g:1473:5: (lv_parameters_7_0= ruleParameter )
                    {
                    // InternalMORA.g:1473:5: (lv_parameters_7_0= ruleParameter )
                    // InternalMORA.g:1474:6: lv_parameters_7_0= ruleParameter
                    {

                    						newCompositeNode(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_0_0());
                    					
                    pushFollow(FOLLOW_39);
                    lv_parameters_7_0=ruleParameter();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMethodRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_7_0,
                    							"de.sos.MORA.Parameter");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMORA.g:1491:4: (otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==31) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalMORA.g:1492:5: otherlv_8= ',' ( (lv_parameters_9_0= ruleParameter ) )
                    	    {
                    	    otherlv_8=(Token)match(input,31,FOLLOW_29); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getMethodAccess().getCommaKeyword_4_1_0());
                    	    				
                    	    // InternalMORA.g:1496:5: ( (lv_parameters_9_0= ruleParameter ) )
                    	    // InternalMORA.g:1497:6: (lv_parameters_9_0= ruleParameter )
                    	    {
                    	    // InternalMORA.g:1497:6: (lv_parameters_9_0= ruleParameter )
                    	    // InternalMORA.g:1498:7: lv_parameters_9_0= ruleParameter
                    	    {

                    	    							newCompositeNode(grammarAccess.getMethodAccess().getParametersParameterParserRuleCall_4_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_39);
                    	    lv_parameters_9_0=ruleParameter();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getMethodRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"parameters",
                    	    								lv_parameters_9_0,
                    	    								"de.sos.MORA.Parameter");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_10=(Token)match(input,34,FOLLOW_40); 

            			newLeafNode(otherlv_10, grammarAccess.getMethodAccess().getRightParenthesisKeyword_5());
            		
            // InternalMORA.g:1521:3: (otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )* )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==35) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalMORA.g:1522:4: otherlv_11= 'throws' ( (lv_exceptions_12_0= ruleException ) ) (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )*
                    {
                    otherlv_11=(Token)match(input,35,FOLLOW_41); 

                    				newLeafNode(otherlv_11, grammarAccess.getMethodAccess().getThrowsKeyword_6_0());
                    			
                    // InternalMORA.g:1526:4: ( (lv_exceptions_12_0= ruleException ) )
                    // InternalMORA.g:1527:5: (lv_exceptions_12_0= ruleException )
                    {
                    // InternalMORA.g:1527:5: (lv_exceptions_12_0= ruleException )
                    // InternalMORA.g:1528:6: lv_exceptions_12_0= ruleException
                    {

                    						newCompositeNode(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_1_0());
                    					
                    pushFollow(FOLLOW_42);
                    lv_exceptions_12_0=ruleException();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMethodRule());
                    						}
                    						add(
                    							current,
                    							"exceptions",
                    							lv_exceptions_12_0,
                    							"de.sos.MORA.Exception");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMORA.g:1545:4: (otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==31) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // InternalMORA.g:1546:5: otherlv_13= ',' ( (lv_exceptions_14_0= ruleException ) )
                    	    {
                    	    otherlv_13=(Token)match(input,31,FOLLOW_41); 

                    	    					newLeafNode(otherlv_13, grammarAccess.getMethodAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalMORA.g:1550:5: ( (lv_exceptions_14_0= ruleException ) )
                    	    // InternalMORA.g:1551:6: (lv_exceptions_14_0= ruleException )
                    	    {
                    	    // InternalMORA.g:1551:6: (lv_exceptions_14_0= ruleException )
                    	    // InternalMORA.g:1552:7: lv_exceptions_14_0= ruleException
                    	    {

                    	    							newCompositeNode(grammarAccess.getMethodAccess().getExceptionsExceptionParserRuleCall_6_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_42);
                    	    lv_exceptions_14_0=ruleException();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getMethodRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"exceptions",
                    	    								lv_exceptions_14_0,
                    	    								"de.sos.MORA.Exception");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleException"
    // InternalMORA.g:1575:1: entryRuleException returns [EObject current=null] : iv_ruleException= ruleException EOF ;
    public final EObject entryRuleException() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleException = null;


        try {
            // InternalMORA.g:1575:50: (iv_ruleException= ruleException EOF )
            // InternalMORA.g:1576:2: iv_ruleException= ruleException EOF
            {
             newCompositeNode(grammarAccess.getExceptionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleException=ruleException();

            state._fsp--;

             current =iv_ruleException; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleException"


    // $ANTLR start "ruleException"
    // InternalMORA.g:1582:1: ruleException returns [EObject current=null] : (otherlv_0= 'exception' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )* otherlv_5= '}' ) ;
    public final EObject ruleException() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_member_3_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:1588:2: ( (otherlv_0= 'exception' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )* otherlv_5= '}' ) )
            // InternalMORA.g:1589:2: (otherlv_0= 'exception' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )* otherlv_5= '}' )
            {
            // InternalMORA.g:1589:2: (otherlv_0= 'exception' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )* otherlv_5= '}' )
            // InternalMORA.g:1590:3: otherlv_0= 'exception' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getExceptionAccess().getExceptionKeyword_0());
            		
            // InternalMORA.g:1594:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMORA.g:1595:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMORA.g:1595:4: (lv_name_1_0= RULE_ID )
            // InternalMORA.g:1596:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_name_1_0, grammarAccess.getExceptionAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExceptionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"de.sos.MORA.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_19); 

            			newLeafNode(otherlv_2, grammarAccess.getExceptionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalMORA.g:1616:3: ( ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )? )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==RULE_ID||LA39_0==RULE_ML_COMMENT||LA39_0==23||(LA39_0>=39 && LA39_0<=47)) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalMORA.g:1617:4: ( (lv_member_3_0= ruleMember ) ) (otherlv_4= ';' )?
            	    {
            	    // InternalMORA.g:1617:4: ( (lv_member_3_0= ruleMember ) )
            	    // InternalMORA.g:1618:5: (lv_member_3_0= ruleMember )
            	    {
            	    // InternalMORA.g:1618:5: (lv_member_3_0= ruleMember )
            	    // InternalMORA.g:1619:6: lv_member_3_0= ruleMember
            	    {

            	    						newCompositeNode(grammarAccess.getExceptionAccess().getMemberMemberParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_20);
            	    lv_member_3_0=ruleMember();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getExceptionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"member",
            	    							lv_member_3_0,
            	    							"de.sos.MORA.Member");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalMORA.g:1636:4: (otherlv_4= ';' )?
            	    int alt38=2;
            	    int LA38_0 = input.LA(1);

            	    if ( (LA38_0==13) ) {
            	        alt38=1;
            	    }
            	    switch (alt38) {
            	        case 1 :
            	            // InternalMORA.g:1637:5: otherlv_4= ';'
            	            {
            	            otherlv_4=(Token)match(input,13,FOLLOW_19); 

            	            					newLeafNode(otherlv_4, grammarAccess.getExceptionAccess().getSemicolonKeyword_3_1());
            	            				

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

            otherlv_5=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getExceptionAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleException"


    // $ANTLR start "entryRuleParameter"
    // InternalMORA.g:1651:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalMORA.g:1651:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalMORA.g:1652:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalMORA.g:1658:1: ruleParameter returns [EObject current=null] : ( ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) ) ( (lv_name_4_0= RULE_ID ) ) ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;
        Enumerator lv_primType_1_0 = null;



        	enterRule();

        try {
            // InternalMORA.g:1664:2: ( ( ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) ) ( (lv_name_4_0= RULE_ID ) ) ) )
            // InternalMORA.g:1665:2: ( ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) ) ( (lv_name_4_0= RULE_ID ) ) )
            {
            // InternalMORA.g:1665:2: ( ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) ) ( (lv_name_4_0= RULE_ID ) ) )
            // InternalMORA.g:1666:3: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) ) ( (lv_name_4_0= RULE_ID ) )
            {
            // InternalMORA.g:1666:3: ( ( ( ruleQualifiedName ) ) | ( (lv_primType_1_0= rulePrimTypeLiteral ) ) | ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' ) )
            int alt40=3;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==RULE_ID) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==RULE_ID||(LA40_1>=37 && LA40_1<=38)) ) {
                    alt40=1;
                }
                else if ( (LA40_1==32) ) {
                    alt40=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA40_0>=39 && LA40_0<=47)) ) {
                alt40=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // InternalMORA.g:1667:4: ( ( ruleQualifiedName ) )
                    {
                    // InternalMORA.g:1667:4: ( ( ruleQualifiedName ) )
                    // InternalMORA.g:1668:5: ( ruleQualifiedName )
                    {
                    // InternalMORA.g:1668:5: ( ruleQualifiedName )
                    // InternalMORA.g:1669:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getParameterAccess().getComplexTypeTypeDeclCrossReference_0_0_0());
                    					
                    pushFollow(FOLLOW_5);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:1684:4: ( (lv_primType_1_0= rulePrimTypeLiteral ) )
                    {
                    // InternalMORA.g:1684:4: ( (lv_primType_1_0= rulePrimTypeLiteral ) )
                    // InternalMORA.g:1685:5: (lv_primType_1_0= rulePrimTypeLiteral )
                    {
                    // InternalMORA.g:1685:5: (lv_primType_1_0= rulePrimTypeLiteral )
                    // InternalMORA.g:1686:6: lv_primType_1_0= rulePrimTypeLiteral
                    {

                    						newCompositeNode(grammarAccess.getParameterAccess().getPrimTypePrimTypeLiteralEnumRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_5);
                    lv_primType_1_0=rulePrimTypeLiteral();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getParameterRule());
                    						}
                    						set(
                    							current,
                    							"primType",
                    							lv_primType_1_0,
                    							"de.sos.MORA.PrimTypeLiteral");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:1704:4: ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' )
                    {
                    // InternalMORA.g:1704:4: ( ( (otherlv_2= RULE_ID ) ) otherlv_3= '*' )
                    // InternalMORA.g:1705:5: ( (otherlv_2= RULE_ID ) ) otherlv_3= '*'
                    {
                    // InternalMORA.g:1705:5: ( (otherlv_2= RULE_ID ) )
                    // InternalMORA.g:1706:6: (otherlv_2= RULE_ID )
                    {
                    // InternalMORA.g:1706:6: (otherlv_2= RULE_ID )
                    // InternalMORA.g:1707:7: otherlv_2= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getParameterRule());
                    							}
                    						
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_36); 

                    							newLeafNode(otherlv_2, grammarAccess.getParameterAccess().getProxyTypeInterfaceCrossReference_0_2_0_0());
                    						

                    }


                    }

                    otherlv_3=(Token)match(input,32,FOLLOW_5); 

                    					newLeafNode(otherlv_3, grammarAccess.getParameterAccess().getAsteriskKeyword_0_2_1());
                    				

                    }


                    }
                    break;

            }

            // InternalMORA.g:1724:3: ( (lv_name_4_0= RULE_ID ) )
            // InternalMORA.g:1725:4: (lv_name_4_0= RULE_ID )
            {
            // InternalMORA.g:1725:4: (lv_name_4_0= RULE_ID )
            // InternalMORA.g:1726:5: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_4_0, grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"de.sos.MORA.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalMORA.g:1746:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalMORA.g:1746:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalMORA.g:1747:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalMORA.g:1753:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_3=null;


        	enterRule();

        try {
            // InternalMORA.g:1759:2: ( (this_ID_0= RULE_ID ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )* ) )
            // InternalMORA.g:1760:2: (this_ID_0= RULE_ID ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )* )
            {
            // InternalMORA.g:1760:2: (this_ID_0= RULE_ID ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )* )
            // InternalMORA.g:1761:3: this_ID_0= RULE_ID ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_43); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0());
            		
            // InternalMORA.g:1768:3: ( (kw= '.' | kw= '::' ) this_ID_3= RULE_ID )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=37 && LA42_0<=38)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalMORA.g:1769:4: (kw= '.' | kw= '::' ) this_ID_3= RULE_ID
            	    {
            	    // InternalMORA.g:1769:4: (kw= '.' | kw= '::' )
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==37) ) {
            	        alt41=1;
            	    }
            	    else if ( (LA41_0==38) ) {
            	        alt41=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 41, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // InternalMORA.g:1770:5: kw= '.'
            	            {
            	            kw=(Token)match(input,37,FOLLOW_5); 

            	            					current.merge(kw);
            	            					newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0_0());
            	            				

            	            }
            	            break;
            	        case 2 :
            	            // InternalMORA.g:1776:5: kw= '::'
            	            {
            	            kw=(Token)match(input,38,FOLLOW_5); 

            	            					current.merge(kw);
            	            					newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getColonColonKeyword_1_0_1());
            	            				

            	            }
            	            break;

            	    }

            	    this_ID_3=(Token)match(input,RULE_ID,FOLLOW_43); 

            	    				current.merge(this_ID_3);
            	    			

            	    				newLeafNode(this_ID_3, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "rulePrimTypeLiteral"
    // InternalMORA.g:1794:1: rulePrimTypeLiteral returns [Enumerator current=null] : ( (enumLiteral_0= 'bool' ) | (enumLiteral_1= 'byte' ) | (enumLiteral_2= 'short' ) | (enumLiteral_3= 'int' ) | (enumLiteral_4= 'long' ) | (enumLiteral_5= 'float' ) | (enumLiteral_6= 'double' ) | (enumLiteral_7= 'string' ) | (enumLiteral_8= 'void' ) ) ;
    public final Enumerator rulePrimTypeLiteral() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;


        	enterRule();

        try {
            // InternalMORA.g:1800:2: ( ( (enumLiteral_0= 'bool' ) | (enumLiteral_1= 'byte' ) | (enumLiteral_2= 'short' ) | (enumLiteral_3= 'int' ) | (enumLiteral_4= 'long' ) | (enumLiteral_5= 'float' ) | (enumLiteral_6= 'double' ) | (enumLiteral_7= 'string' ) | (enumLiteral_8= 'void' ) ) )
            // InternalMORA.g:1801:2: ( (enumLiteral_0= 'bool' ) | (enumLiteral_1= 'byte' ) | (enumLiteral_2= 'short' ) | (enumLiteral_3= 'int' ) | (enumLiteral_4= 'long' ) | (enumLiteral_5= 'float' ) | (enumLiteral_6= 'double' ) | (enumLiteral_7= 'string' ) | (enumLiteral_8= 'void' ) )
            {
            // InternalMORA.g:1801:2: ( (enumLiteral_0= 'bool' ) | (enumLiteral_1= 'byte' ) | (enumLiteral_2= 'short' ) | (enumLiteral_3= 'int' ) | (enumLiteral_4= 'long' ) | (enumLiteral_5= 'float' ) | (enumLiteral_6= 'double' ) | (enumLiteral_7= 'string' ) | (enumLiteral_8= 'void' ) )
            int alt43=9;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt43=1;
                }
                break;
            case 40:
                {
                alt43=2;
                }
                break;
            case 41:
                {
                alt43=3;
                }
                break;
            case 42:
                {
                alt43=4;
                }
                break;
            case 43:
                {
                alt43=5;
                }
                break;
            case 44:
                {
                alt43=6;
                }
                break;
            case 45:
                {
                alt43=7;
                }
                break;
            case 46:
                {
                alt43=8;
                }
                break;
            case 47:
                {
                alt43=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // InternalMORA.g:1802:3: (enumLiteral_0= 'bool' )
                    {
                    // InternalMORA.g:1802:3: (enumLiteral_0= 'bool' )
                    // InternalMORA.g:1803:4: enumLiteral_0= 'bool'
                    {
                    enumLiteral_0=(Token)match(input,39,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getBOOLEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPrimTypeLiteralAccess().getBOOLEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMORA.g:1810:3: (enumLiteral_1= 'byte' )
                    {
                    // InternalMORA.g:1810:3: (enumLiteral_1= 'byte' )
                    // InternalMORA.g:1811:4: enumLiteral_1= 'byte'
                    {
                    enumLiteral_1=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getBYTEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPrimTypeLiteralAccess().getBYTEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalMORA.g:1818:3: (enumLiteral_2= 'short' )
                    {
                    // InternalMORA.g:1818:3: (enumLiteral_2= 'short' )
                    // InternalMORA.g:1819:4: enumLiteral_2= 'short'
                    {
                    enumLiteral_2=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getSHORTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getPrimTypeLiteralAccess().getSHORTEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalMORA.g:1826:3: (enumLiteral_3= 'int' )
                    {
                    // InternalMORA.g:1826:3: (enumLiteral_3= 'int' )
                    // InternalMORA.g:1827:4: enumLiteral_3= 'int'
                    {
                    enumLiteral_3=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getINTEGEREnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getPrimTypeLiteralAccess().getINTEGEREnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalMORA.g:1834:3: (enumLiteral_4= 'long' )
                    {
                    // InternalMORA.g:1834:3: (enumLiteral_4= 'long' )
                    // InternalMORA.g:1835:4: enumLiteral_4= 'long'
                    {
                    enumLiteral_4=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getLONGEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getPrimTypeLiteralAccess().getLONGEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalMORA.g:1842:3: (enumLiteral_5= 'float' )
                    {
                    // InternalMORA.g:1842:3: (enumLiteral_5= 'float' )
                    // InternalMORA.g:1843:4: enumLiteral_5= 'float'
                    {
                    enumLiteral_5=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getFLOATEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getPrimTypeLiteralAccess().getFLOATEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalMORA.g:1850:3: (enumLiteral_6= 'double' )
                    {
                    // InternalMORA.g:1850:3: (enumLiteral_6= 'double' )
                    // InternalMORA.g:1851:4: enumLiteral_6= 'double'
                    {
                    enumLiteral_6=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getDOUBLEEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getPrimTypeLiteralAccess().getDOUBLEEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalMORA.g:1858:3: (enumLiteral_7= 'string' )
                    {
                    // InternalMORA.g:1858:3: (enumLiteral_7= 'string' )
                    // InternalMORA.g:1859:4: enumLiteral_7= 'string'
                    {
                    enumLiteral_7=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getSTRINGEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getPrimTypeLiteralAccess().getSTRINGEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalMORA.g:1866:3: (enumLiteral_8= 'void' )
                    {
                    // InternalMORA.g:1866:3: (enumLiteral_8= 'void' )
                    // InternalMORA.g:1867:4: enumLiteral_8= 'void'
                    {
                    enumLiteral_8=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getPrimTypeLiteralAccess().getVOIDEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getPrimTypeLiteralAccess().getVOIDEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimTypeLiteral"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String dfa_1s = "\10\uffff";
    static final String dfa_2s = "\1\6\1\27\1\4\2\uffff\1\23\1\5\1\27";
    static final String dfa_3s = "\1\57\1\35\1\4\2\uffff\1\35\1\5\1\35";
    static final String dfa_4s = "\3\uffff\1\1\1\2\3\uffff";
    static final String dfa_5s = "\10\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\20\uffff\1\2\3\4\2\uffff\1\3\11\uffff\11\4",
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

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "145:4: ( ( (lv_interfaces_5_0= ruleInterface ) ) | ( (lv_types_6_0= ruleTypeDecl ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000018800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000FF8027804040L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000FF8027806040L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000520000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000524000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000FF8000804050L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000FF8000806050L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000FF8000800010L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000050L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000006050L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000004050L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000FF8000000010L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000040001000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000080001000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000FF8000004050L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000FF8000006050L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000FF8400000010L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000480000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000006000000002L});

}