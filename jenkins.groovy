#!groovy

awsTenantAccountAlias='digital-safety-uat-aws'
environment='np2'
deploymentType='green'
repurl='https://github.allstate.com/DigitalSafety/DS-Deployment-Refactor-Poc'
credId='SYS-ISJenkins-Token-ID'
def jobPath = env.JOB_NAME.split('/')
lambdaFunctionName=jobPath[-2]

node(awsTenantAccountAlias){
    stage ("Checkout") {
        deleteDir()
        checkout([
            $class: 'GitSCM',
            branches: [[name:'master']],
            doGenerateSubmoduleConfigurations: false,
            extensions: [[
              $class: 'CloneOption',
              depth: 0,
              noTags: false,
              reference: '',
              shallow: true],
                [$class: 'RelativeTargetDirectory']],
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: 'SYS-ISJenkins-Token-ID', url: 'https://github.allstate.com/DigitalSafety/DS-Deployment-Refactor-Poc']]])

        load  'pipelines/components/lambda.pipeline'
       // load  'common/*'


    }
  }
